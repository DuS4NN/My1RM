package com.my1rm.controller;

import com.my1rm.configuration.Authentication.CustomUserDetails;
import com.my1rm.model.database.Exercise;
import com.my1rm.model.database.User;
import com.my1rm.repository.ExerciseRepository;
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
class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;

    private RequestPostProcessor user = null;
    private long exerciseId = 0;
    private final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));;


    @PostConstruct
    private void createTestUser(){
        Optional<User> maybeUser = userRepository.findById(7L);
        if(maybeUser.isPresent()){
            this.user = SecurityMockMvcRequestPostProcessors.user(new CustomUserDetails(maybeUser.get()));

            Optional<Exercise> maybeExercise = exerciseRepository.findFirstByUser(maybeUser.get());
            maybeExercise.ifPresent(exercise -> this.exerciseId = maybeExercise.get().getId());
        }
    }

    @Test
    void testGetAllExercises() throws Exception{
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "SUCCESS");

        mockMvc.perform(MockMvcRequestBuilders
            .get("/exercise/getAllExercises")
            .param("orderBy", "DATE")
            .with(user)
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
            .contentType(APPLICATION_JSON_UTF8)
            .content(jsonObject.toString())
            .with(user)
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }

    @Test
    void testRemoveExercise() throws Exception{
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "EXERCISE_REMOVED");

        mockMvc.perform(MockMvcRequestBuilders
            .delete("/exercise/removeExercise")
            .param("exerciseId", String.valueOf(exerciseId))
            .with(user)
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }

    @Test
    void testUpdateExercise() throws Exception{
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "EXERCISE_UPDATED");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", exerciseId);
        jsonObject.put("name", "TestExercise");
        jsonObject.put("goal", 100);

        mockMvc.perform(MockMvcRequestBuilders
            .put("/exercise/updateExercise")
            .contentType(APPLICATION_JSON_UTF8)
            .content(jsonObject.toString())
            .with(user)
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().json(jsonResponse.toString(), false));
    }
}
