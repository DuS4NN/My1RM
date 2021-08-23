package com.my1rm.controller;

import com.my1rm.annotation.CatchError;
import com.my1rm.api.API;
import com.my1rm.model.Response;
import com.my1rm.model.database.User;
import com.my1rm.model.ResponseMessage;
import com.my1rm.validator.ValidationItems;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class AttemptController {

    @GetMapping(path = "/test")
    @CatchError
    public Response test(String name, String password, User user) {

        Optional<Response> response = API.validate(new HashMap<ValidationItems, Object>() {{
            put(ValidationItems.UserEmail, "dusan7991@gmail.com");
        }});
        if(response.isPresent()) return response.get();

        //dd

        return new Response(ResponseMessage.CommonResponseMessage.SUCCESS, "AHOJ");
    }
}
