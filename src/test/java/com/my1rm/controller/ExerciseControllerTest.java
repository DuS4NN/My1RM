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
class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ConfigurationTest configurationTest;

    @Test
    void testGetAllExercises() throws Exception{
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "SUCCESS");

        mockMvc.perform(MockMvcRequestBuilders
            .get("/exercise/getAllExercises")
            .param("orderBy", "DATE")
            .with(configurationTest.getUser())
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }

    @Test
    void testAddExercise() throws Exception{
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "EXERCISE_CREATED");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "TestExercise");
        jsonObject.put("goal", 100);

        mockMvc.perform(MockMvcRequestBuilders
            .post("/exercise/addExercise")
            .contentType(configurationTest.getMediaType())
            .content(jsonObject.toString())
            .with(configurationTest.getUser())
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }

    @Test
    void testRemoveExercise() throws Exception{
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "EXERCISE_REMOVED");

        mockMvc.perform(MockMvcRequestBuilders
            .delete("/exercise/removeExercise")
            .param("exerciseId", String.valueOf(configurationTest.getExerciseId()))
            .with(configurationTest.getUser())
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }

    @Test
    void testUpdateExercise() throws Exception{
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "EXERCISE_UPDATED");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", configurationTest.getExerciseId());
        jsonObject.put("name", "TestExercise");
        jsonObject.put("goal", 100);

        mockMvc.perform(MockMvcRequestBuilders
            .put("/exercise/updateExercise")
            .contentType(configurationTest.getMediaType())
            .content(jsonObject.toString())
            .with(configurationTest.getUser())
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }
}
