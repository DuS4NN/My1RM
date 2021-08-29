package com.my1rm.configuration.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my1rm.api.API;
import com.my1rm.model.database.User;
import com.my1rm.model.database.UserOption;
import com.my1rm.model.types.WeightUnit;
import com.my1rm.repository.LanguageRepository;
import com.my1rm.repository.UserOptionsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper;
    @Autowired
    private UserOptionsRepository userOptionsRepository;
    @Autowired
    private LanguageRepository languageRepository;

    public CustomAuthenticationSuccessHandler(){
        this.objectMapper = new ObjectMapper();
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = API.getUserFromAuthentication(authentication);

        Map<String, Object> data = new HashMap<>();

        Optional<UserOption> userOptions = userOptionsRepository.findByUser(user);
        if(userOptions.isPresent()){
            data.put("languageId", userOptions.get().getLanguage().getId());
            data.put("weightUnit", userOptions.get().getWeight_unit());
        }else{
            data.put("languageId", languageRepository.findFirstByOrderById().getId());
            data.put("weightUnit", WeightUnit.KG);
        }

        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getOutputStream().println(objectMapper.writeValueAsString(data));
    }
}