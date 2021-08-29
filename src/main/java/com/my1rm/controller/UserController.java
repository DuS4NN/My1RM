package com.my1rm.controller;

import com.my1rm.annotation.CatchError;
import com.my1rm.api.API;
import com.my1rm.model.Response;
import com.my1rm.model.database.User;
import com.my1rm.service.UserService;
import com.my1rm.validator.ValidationItems;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class UserController {

    private UserService userService;

    @CatchError
    @PostMapping("/user/createAccount")
    public Response createAccount(@RequestBody User user, @RequestParam(name = "languageId") long languageId){
        Optional<Response> response = API.validate(new HashMap<ValidationItems, Object>() {{
            put(ValidationItems.UserEmail, user.getEmail());
            put(ValidationItems.UserPassword, user.getPassword());
        }});
        return response.orElseGet(() -> userService.createAccount(user, languageId));
    }

    @CatchError
    @PostMapping("/user/verifyAccount")
    public Response verifyAccount(@RequestParam(name = "email") String email, @RequestParam(name = "hash") String hash){
        return userService.verifyAccount(email, hash);
    }

    @CatchError
    @PostMapping("/user/passwordRequest")
    public Response createResetPasswordRequest(@RequestParam(name = "email") String email){
        return userService.createResetPasswordRequest(email);
    }

    @CatchError
    @PostMapping("/user/resetPassword")
    public Response resetPassword(@RequestParam(name = "hash") String hash, @RequestParam(name = "password") String password){
        Optional<Response> response = API.validate(new HashMap<ValidationItems, Object>() {{
            put(ValidationItems.UserPassword, password);
        }});
        return response.orElseGet(() -> userService.resetPassword(hash, password));
    }

    @CatchError
    @PostMapping("/user/recreateVerificationRequest")
    public Response recreateVerificationRequest(@RequestParam(name = "email") String email){
        return userService.recreateVerificationRequest(email);
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/user/changePassword")
    public Response changePassword(Authentication authentication, @RequestParam(name = "oldPassword") String oldPassword, @RequestParam(name = "newPassword") String newPassword){
        User user = API.getUserFromAuthentication(authentication);
        Optional<Response> response = API.validate(new HashMap<ValidationItems, Object>() {{
            put(ValidationItems.UserPassword, newPassword);
        }});
        return response.orElseGet(() -> userService.changePassword(user, oldPassword, newPassword));
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/user/changeEmail")
    public Response changeEmail(Authentication authentication, @RequestParam(name = "newEmail") String newEmail, @RequestParam(name = "password") String password){
        User user = API.getUserFromAuthentication(authentication);
        Optional<Response> response = API.validate(new HashMap<ValidationItems, Object>() {{
            put(ValidationItems.UserEmail, newEmail);
        }});
        return response.orElseGet(() -> userService.changeEmail(user, newEmail, password));
    }
}
