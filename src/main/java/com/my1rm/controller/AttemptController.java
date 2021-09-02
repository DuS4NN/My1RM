package com.my1rm.controller;

import com.my1rm.annotation.CatchError;
import com.my1rm.api.API;
import com.my1rm.model.Response;
import com.my1rm.model.database.Attempt;
import com.my1rm.model.database.User;
import com.my1rm.service.AttemptService;
import com.my1rm.validator.ValidationItems;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class AttemptController {

    private AttemptService attemptService;

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/attempt/getDataForGraph")
    public Response getDataForGraph(Authentication authentication, @RequestParam(name = "exerciseId") long exerciseId, @RequestParam(name = "seasonId") long seasonId, @RequestParam(name = "success") String success, @RequestParam(name = "repetitions") short repetitions){
        User user = API.getUserFromAuthentication(authentication);
        return attemptService.getDataForGraph(user.getId(), exerciseId, success, seasonId, repetitions);
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/attempt/getBestAttempt")
    public Response getBestAttempt(Authentication authentication, @RequestParam(name = "exerciseId") long exerciseId){
        User user = API.getUserFromAuthentication(authentication);
        return attemptService.getBestAttempt(user.getId(), exerciseId);
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/attempt/getAttempts")
    public Response getAttempts(Authentication authentication, @RequestParam(name = "exerciseId") long exerciseId, @RequestParam(name = "page") int page) {
        User user = API.getUserFromAuthentication(authentication);
        return attemptService.getAttempts(user.getId(), exerciseId, page);
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/attempt/addAttempt")
    public Response addAttempt(Authentication authentication, @RequestBody Attempt attempt, @RequestParam(name = "exerciseId") long exerciseId, @RequestParam(name = "seasonId") long seasonId){
        User user = API.getUserFromAuthentication(authentication);
        Optional<Response> response = API.validate(new HashMap<ValidationItems, Object>() {{
            put(ValidationItems.AttemptRepetitions, attempt.getRepetitions());
            put(ValidationItems.AttemptWeight, attempt.getWeight());
        }});
        return response.orElseGet(() -> attemptService.addAttempt(user, attempt, exerciseId, seasonId));
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(path = "/attempt/removeAttempt")
    public Response addAttempt(Authentication authentication, @RequestParam(name = "attemptId") long attemptId){
        User user = API.getUserFromAuthentication(authentication);
        return attemptService.removeAttempt(user, attemptId);
    }
}
