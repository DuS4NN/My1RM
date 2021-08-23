package com.my1rm.configuration.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my1rm.model.ResponseMessage;
import com.my1rm.model.database.User;
import com.my1rm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper;
    @Autowired
    UserRepository userRepository;

    public CustomAuthenticationFailureHandler(){
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {

        Map<String, Object> data = new HashMap<>();
        data.put("data", null);

        Optional<User> user = userRepository.findByEmail(request.getParameter("username"));
        if(!user.isPresent()){
            data.put("message", ResponseMessage.UserResponseMessage.ACCOUNT_DOES_NOT_EXIST);
        }else if(exception.getMessage().equals("User is disabled")){
            data.put("message", ResponseMessage.UserResponseMessage.UNVERIFIED_ACCOUNT);
        }else{
            data.put("message", ResponseMessage.UserResponseMessage.WRONG_CREDENTIALS);
        }

        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getOutputStream().println(objectMapper.writeValueAsString(data));
    }
}