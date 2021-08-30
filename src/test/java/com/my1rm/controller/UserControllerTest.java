package com.my1rm.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ConfigurationTest configurationTest;

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
            .contentType(configurationTest.getMediaType())
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
                .with(configurationTest.getUser())
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
                .with(configurationTest.getUser())
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }
}
