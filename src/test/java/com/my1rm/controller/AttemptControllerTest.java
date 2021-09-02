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
class AttemptControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ConfigurationTest configurationTest;

     @Test
     void testGetAttempts() throws Exception {
         JSONObject jsonResponse = new JSONObject();
         jsonResponse.put("message", "SUCCESS");

         mockMvc.perform(MockMvcRequestBuilders
                 .get("/attempt/getAttempts")
                 .param("exerciseId", String.valueOf(11))
                 .param("page", String.valueOf(1))
                 .with(configurationTest.getUser())
         ).andDo(MockMvcResultHandlers.print())
                 .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
     }

    @Test
    void testAddAttempt() throws Exception {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "ATTEMPT_CREATED");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("repetitions", 5);
        jsonObject.put("success", 1);
        jsonObject.put("weight", 112.5);
        jsonObject.put("date", "2021-9-2");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/attempt/addAttempt")
                .param("exerciseId", String.valueOf(11))
                .param("seasonId", String.valueOf(9))
                .contentType(configurationTest.getMediaType())
                .content(jsonObject.toString())
                .with(configurationTest.getUser())
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }

    @Test
    void testRemoveAttempt() throws Exception {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "ATTEMPT_REMOVED");

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/attempt/removeAttempt")
                .param("attemptId", String.valueOf(17))
                .with(configurationTest.getUser())
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }
}
