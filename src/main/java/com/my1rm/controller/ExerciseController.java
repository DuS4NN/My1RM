package com.my1rm.controller;

import com.my1rm.annotation.CatchError;
import com.my1rm.api.API;
import com.my1rm.model.Response;
import com.my1rm.model.database.Exercise;
import com.my1rm.model.database.User;
import com.my1rm.service.ExerciseService;
import com.my1rm.validator.ValidationItems;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class ExerciseController {

    private ExerciseService exerciseService;

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/exercise/getAllExercises")
    public Response getAllExercises(Authentication authentication, @RequestParam(name = "orderBy") String orderBy){
        User user = API.getUserFromAuthentication(authentication);
        return exerciseService.getAllExercises(user.getId(), orderBy);
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/exercise/addExercise")
    public Response addExercise(Authentication authentication, @RequestBody Exercise exercise){
        User user = API.getUserFromAuthentication(authentication);
        Optional<Response> response = API.validate(new HashMap<ValidationItems, Object>() {{
            put(ValidationItems.ExerciseName, exercise.getName());
            put(ValidationItems.ExerciseGoal, exercise.getGoal());
        }});
        return response.orElseGet(() -> exerciseService.addExercise(exercise, user));
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/exercise/removeExercise")
    public Response removeExercise(Authentication authentication, @RequestParam(name = "exerciseId") long exerciseId){
        User user = API.getUserFromAuthentication(authentication);
        return exerciseService.removeExercise(exerciseId, user);
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/exercise/updateExercise")
    public Response updateExercise(Authentication authentication, @RequestBody Exercise exercise){
        User user = API.getUserFromAuthentication(authentication);
        Optional<Response> response = API.validate(new HashMap<ValidationItems, Object>() {{
            put(ValidationItems.ExerciseName, exercise.getName());
            put(ValidationItems.ExerciseGoal, exercise.getGoal());
        }});
        return response.orElseGet(() -> exerciseService.updateExercise(exercise, user));
    }
}
