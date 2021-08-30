package com.my1rm.controller;

import com.my1rm.annotation.CatchError;
import com.my1rm.api.API;
import com.my1rm.model.Response;
import com.my1rm.model.database.Season;
import com.my1rm.model.database.User;
import com.my1rm.service.SeasonService;
import com.my1rm.validator.ValidationItems;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class SeasonController {

    private SeasonService seasonService;

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/season/getAllSeasons")
    public Response getAllSeasons(Authentication authentication, @RequestParam(name = "orderBy") String orderBy){
        User user = API.getUserFromAuthentication(authentication);
        return seasonService.getAllSeasons(user.getId(), orderBy);
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/season/addSeason")
    public Response addSeason(Authentication authentication, @RequestBody Season season){
        User user = API.getUserFromAuthentication(authentication);
        Optional<Response> response = API.validate(new HashMap<ValidationItems, Object>() {{
            put(ValidationItems.SeasonColor, season.getColor());
            put(ValidationItems.SeasonName, season.getName());
        }});
        return response.orElseGet(() -> seasonService.addSeason(season, user));
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/season/removeSeason")
    public Response removeSeason(Authentication authentication, @RequestParam(name = "seasonId") long seasonId, @RequestParam(name = "replaceBy") long replaceBy){
        User user = API.getUserFromAuthentication(authentication);
        return seasonService.removeSeason(seasonId, user, replaceBy);
    }

    @CatchError
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/season/updateSeason")
    public Response updateSeason(Authentication authentication, @RequestBody Season season){
        User user = API.getUserFromAuthentication(authentication);
        Optional<Response> response = API.validate(new HashMap<ValidationItems, Object>() {{
            put(ValidationItems.SeasonColor, season.getColor());
            put(ValidationItems.SeasonName, season.getName());
        }});
        return response.orElseGet(() -> seasonService.updateSeason(season, user));
    }
}
