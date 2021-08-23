package com.my1rm.service;

import com.my1rm.model.Response;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExerciseService {

    private DSLContext dslContext;

    public Response getAllExercises(long userId){
        return null;
    }
}
