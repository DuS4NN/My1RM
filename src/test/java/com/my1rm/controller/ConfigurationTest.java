package com.my1rm.controller;

import com.my1rm.configuration.Authentication.CustomUserDetails;
import com.my1rm.model.database.Exercise;
import com.my1rm.model.database.User;
import com.my1rm.repository.ExerciseRepository;
import com.my1rm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.Optional;

@Component
public class ConfigurationTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;

    private long exerciseId = 0;
    private RequestPostProcessor user = null;
    private final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));;

    @PostConstruct
    private void createTestUser(){
        Optional<User> maybeUser = userRepository.findById(3L);
        if(maybeUser.isPresent()){
            this.user = SecurityMockMvcRequestPostProcessors.user(new CustomUserDetails(maybeUser.get()));

            Optional<Exercise> maybeExercise = exerciseRepository.findFirstByUser(maybeUser.get());
            maybeExercise.ifPresent(exercise -> this.exerciseId = maybeExercise.get().getId());
        }
    }

    RequestPostProcessor getUser(){
        return user;
    }

    MediaType getMediaType(){
        return APPLICATION_JSON_UTF8;
    }

    long getExerciseId(){
        return exerciseId;
    }

}
