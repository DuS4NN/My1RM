package com.my1rm.controller;

import com.my1rm.annotation.CatchError;
import com.my1rm.api.API;
import com.my1rm.model.Response;
import com.my1rm.model.database.User;
import com.my1rm.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ExerciseController {

    private ExerciseService exerciseService;

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/exercise/getAllExercises")
    public Response getAllExercises(Authentication authentication){
        User user = API.getUserFromAuthentication(authentication);
        return exerciseService.getAllExercises(user.getId());
    }
}
