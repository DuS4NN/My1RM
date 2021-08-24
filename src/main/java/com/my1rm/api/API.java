package com.my1rm.api;

import com.my1rm.configuration.Authentication.CustomUserDetails;
import com.my1rm.model.Response;
import com.my1rm.model.database.User;
import com.my1rm.validator.ValidationItems;
import com.my1rm.validator.ValidatorResponse;
import io.sentry.Sentry;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Component
public class API {

    private JavaMailSenderImpl mailSender;

    public static void catchException(Exception e){
        //Sentry.capture(e);
        e.printStackTrace();
    }

    public static User getUserFromAuthentication(Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return customUserDetails.getUser();
    }

    public static Optional<Response> validate(HashMap<ValidationItems, Object> values) {
        for(Map.Entry<ValidationItems, Object> item : values.entrySet()){
            ValidatorResponse validatorResponse = item.getKey().validate(item.getValue());
            if(!validatorResponse.isValid()){
                return Optional.of(new Response(validatorResponse.getMessage(), null));
            }
        }
        return Optional.empty();
    }

    @Async
    public void sendEmail(String content, String email, String subject){
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom("noreply@my1rm.com");
            mimeMessageHelper.setText(content, true);
            mailSender.send(mimeMessage);
        }catch (Exception e){
            catchException(e);
        }

    }
}
