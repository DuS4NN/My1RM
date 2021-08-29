package com.my1rm.service;

import com.my1rm.api.API;
import com.my1rm.model.Response;
import com.my1rm.model.ResponseMessage;
import com.my1rm.model.database.Language;
import com.my1rm.model.database.Token;
import com.my1rm.model.database.User;
import com.my1rm.model.database.UserOptions;
import com.my1rm.model.types.TokenType;
import com.my1rm.model.types.WeightUnit;
import com.my1rm.repository.LanguageRepository;
import com.my1rm.repository.TokenRepository;
import com.my1rm.repository.UserRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private LanguageRepository languageRepository;
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private EntityManager entityManager;
    private Argon2PasswordEncoder argon2PasswordEncoder;
    private API api;

    @Transactional
    public Response createAccount(User user, long languageId){
        Optional<Language> language = languageRepository.findById(languageId);
        if(!language.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.LANGUAGE_NOT_FOUND,null);

        Optional<User> maybeUserByEmail = userRepository.findByEmail(user.getEmail());
        if(maybeUserByEmail.isPresent()) return new Response(ResponseMessage.UserResponseMessage.EMAIL_ALREADY_EXISTS, null);

        User account = new User();
        account.setEmail(user.getEmail());
        account.setPassword(argon2PasswordEncoder.encode(user.getPassword()));
        account.setVerified(false);
        entityManager.persist(account);

        UserOptions userOptions = new UserOptions();
        userOptions.setLanguage(language.get());
        userOptions.setUser(account);
        userOptions.setWeight_unit(WeightUnit.KG);
        entityManager.persist(userOptions);

        Token token = new Token();
        token.setUser(account);
        token.setType(TokenType.CONFIRM_EMAIL);
        token.setHash(DigestUtils.sha256Hex(user.getEmail() + RandomString.make(10)));
        entityManager.persist(token);

        String emailContent = token.getHash();
        api.sendEmail(emailContent, user.getEmail(), "My1RM Verification Email");

        return new Response(ResponseMessage.UserResponseMessage.ACCOUNT_CREATED, null);
    }

    @Transactional
    public Response verifyAccount(String email, String hash){
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.USER_NOT_FOUND, null);

        Optional<Token> token = tokenRepository.findByUserAndHash(user.get(), hash);
        if(!token.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.TOKEN_NOT_FOUND, null);

        user.get().setVerified(true);
        entityManager.merge(user.get());
        entityManager.remove(token.get());

        return new Response(ResponseMessage.UserResponseMessage.ACCOUNT_VERIFIED, null);
    }

    @Transactional
    public Response createResetPasswordRequest(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.USER_NOT_FOUND, null);

        tokenRepository.deleteAllByUserAndType(user.get(), TokenType.PASSWORD_RESET);

        Token token = new Token();
        token.setUser(user.get());
        token.setType(TokenType.PASSWORD_RESET);
        token.setHash(DigestUtils.sha256Hex(user.get().getEmail() + RandomString.make(10)));

        entityManager.persist(token);

        String emailContent = token.getHash();
        api.sendEmail(emailContent, user.get().getEmail(), "My1RM Password Reset");

        return new Response(ResponseMessage.UserResponseMessage.PASSWORD_RESET_REQUEST_CREATED, null);
    }

    @Transactional
    public Response resetPassword(String hash, String password){
        Optional<Token> token = tokenRepository.findByHashAndType(hash, TokenType.PASSWORD_RESET);
        if(!token.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.TOKEN_NOT_FOUND, null);

        //Is token older then 30 days
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date date = calendar.getTime();

        if(token.get().getCreatedAt().before(date)){
            entityManager.remove(token.get());
            return new Response(ResponseMessage.CommonResponseMessage.TOKEN_NOT_FOUND, null);
        }

        User user = token.get().getUser();
        user.setPassword(password);
        entityManager.merge(user);
        entityManager.remove(token.get());

        return new Response(ResponseMessage.UserResponseMessage.PASSWORD_RESTORED, null);
    }

    @Transactional
    public Response recreateVerificationRequest(String email){
        Optional<User> user = userRepository.findByEmailAndVerified(email, false);
        if(!user.isPresent()) return new Response(ResponseMessage.UserResponseMessage.ACCOUNT_IS_VERIFIED_OR_DOES_NOT_EXIST, null);

        tokenRepository.deleteAllByUserAndType(user.get(), TokenType.CONFIRM_EMAIL);

        Token token = new Token();
        token.setUser(user.get());
        token.setType(TokenType.CONFIRM_EMAIL);
        token.setHash(DigestUtils.sha256Hex(user.get().getEmail() + RandomString.make(10)));
        entityManager.persist(token);

        String emailContent = token.getHash();
        api.sendEmail(emailContent, user.get().getEmail(), "My1RM Verification Email");

        return new Response(ResponseMessage.UserResponseMessage.VERIFICATION_EMAIL_RESEND, null);
    }

    @Transactional
    public Response changePassword(User user, String oldPassword, String newPassword){
        if(!argon2PasswordEncoder.matches(oldPassword, user.getPassword())) return new Response(ResponseMessage.UserResponseMessage.WRONG_PASSWORD,null);
        if(!user.isVerified()) return new Response(ResponseMessage.UserResponseMessage.UNVERIFIED_ACCOUNT, null);

        user.setPassword(argon2PasswordEncoder.encode(newPassword));
        entityManager.merge(user);

        return new Response(ResponseMessage.UserResponseMessage.PASSWORD_CHANGED,null);
    }

    @Transactional
    public Response changeEmail(User user, String newEmail, String password){
        if(!argon2PasswordEncoder.matches(password, user.getPassword())) return new Response(ResponseMessage.UserResponseMessage.WRONG_PASSWORD,null);
        String oldEmail = user.getEmail();

        user.setEmail(newEmail);
        user.setVerified(false);
        entityManager.merge(user);

        Token token = new Token();
        token.setUser(user);
        token.setType(TokenType.CONFIRM_EMAIL);
        token.setHash(DigestUtils.sha256Hex(user.getEmail() + RandomString.make(10)));
        entityManager.persist(token);

        String emailContent = token.getHash();

        api.sendEmail("Tvoj email bol zmeneny na novy, ktory je potrebne overit..", oldEmail, "My1RM Email changed");
        api.sendEmail(emailContent, newEmail, "My1RM Verification Email");

        return new Response(ResponseMessage.UserResponseMessage.EMAIL_CHANGED, null);
    }
}
