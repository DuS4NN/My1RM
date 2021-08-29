package com.my1rm.controller;

import com.my1rm.configuration.Authentication.CustomUserDetails;
import com.my1rm.model.database.Exercise;
import com.my1rm.model.database.User;
import com.my1rm.repository.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    private RequestPostProcessor user = null;
    private final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));;

    @PostConstruct
    private void createTestUser(){
        Optional<User> maybeUser = userRepository.findById(7L);
        maybeUser.ifPresent(user1 -> this.user = SecurityMockMvcRequestPostProcessors.user(new CustomUserDetails(user1)));
    }

    //TODO: Setup class for variables and more tests in one method for service. Create more reliable/expected test data in DB.

    @Test
    void testCreateAccount() throws Exception{
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "ACCOUNT_CREATED");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "test@my1rm.com");
        jsonObject.put("password", "password");

        mockMvc.perform(MockMvcRequestBuilders
            .post("/user/createAccount")
            .param("languageId", String.valueOf(1))
            .contentType(APPLICATION_JSON_UTF8)
            .content(jsonObject.toString())
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }

    @Test
    void testChangePassword() throws Exception{
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "PASSWORD_CHANGED");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/user/changePassword")
                .param("oldPassword", "password")
                .param("newPassword", "password")
                .with(user)
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }

    @Test
    void testChangeEmail() throws Exception{
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "EMAIL_CHANGED");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/user/changeEmail")
                .param("newEmail", "newEmail@my1rm.com")
                .param("password", "password")
                .with(user)
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }
}
