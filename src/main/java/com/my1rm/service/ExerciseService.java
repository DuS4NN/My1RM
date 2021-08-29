package com.my1rm.service;

import com.my1rm.api.Tables;
import com.my1rm.model.Response;
import com.my1rm.model.ResponseMessage;
import com.my1rm.model.database.Exercise;
import com.my1rm.model.database.User;
import com.my1rm.model.pojo.ExercisePojo.GetAllExercisesPOJO;
import com.my1rm.repository.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ExerciseService {

    private ExerciseRepository exerciseRepository;
    private DSLContext dslContext;
    private EntityManager entityManager;
    private Tables tables;

    public Response getAllExercises(long userId, String orderBy){

        Table<?> bestUserAttempts = tables.getBestAttemptsTable(userId);

        //Order by
        SortField[] sortField = {com.my1rm.jooq.tables.Exercise.EXERCISE.CREATED_AT.desc()};
        if(orderBy.contains("NAME")){
            sortField = new SortField[]{com.my1rm.jooq.tables.Exercise.EXERCISE.NAME.asc()};
        } else if(orderBy.contains("WEIGHT")){
             sortField = new SortField[] {bestUserAttempts.field("bestAttempt").desc()};
        }

        List<GetAllExercisesPOJO> result = dslContext.select(com.my1rm.jooq.tables.Exercise.EXERCISE.ID.as("exerciseId"),
                        com.my1rm.jooq.tables.Exercise.EXERCISE.GOAL.as("exerciseGoal"),
                        com.my1rm.jooq.tables.Exercise.EXERCISE.NAME.as("exerciseName"),
                        bestUserAttempts.field("bestAttempt").as("bestAttempt"))
                .from(com.my1rm.jooq.tables.Exercise.EXERCISE)
                .leftJoin(bestUserAttempts)
                    .on(com.my1rm.jooq.tables.Exercise.EXERCISE.ID
                        .eq(DSL.coerce(bestUserAttempts.field("exerciseId"), Long.class)))
                .where(com.my1rm.jooq.tables.Exercise.EXERCISE.USER_ID.eq(userId))
                .orderBy(sortField)
                .fetchInto(GetAllExercisesPOJO.class);

        return new Response(ResponseMessage.CommonResponseMessage.SUCCESS, result);
    }

    @Transactional
    public Response addExercise(Exercise exercise, User user){
        Optional<Exercise> maybeExercise = exerciseRepository.findByNameAndUser(exercise.getName(), user);
        if(maybeExercise.isPresent()) return new Response(ResponseMessage.ExerciseResponseMessage.NAME_ALREADY_EXISTS ,null);

        if(exerciseRepository.countAllByUser(user) >= 30) return new Response(ResponseMessage.ExerciseResponseMessage.MAXIMUM_NUMBER_OF_EXERCISES ,null);

        Exercise newExercise = new Exercise();
        newExercise.setGoal(exercise.getGoal());
        newExercise.setName(exercise.getName());
        newExercise.setUser(user);
        entityManager.persist(newExercise);

        HashMap<String, Object> result = new HashMap<>();
        result.put("exerciseId", newExercise.getId());
        result.put("exerciseGoal", newExercise.getGoal());
        result.put("exerciseName", newExercise.getName());
        result.put("bestAttempt", 0);

        return new Response(ResponseMessage.ExerciseResponseMessage.EXERCISE_CREATED, result);
    }

    @Transactional
    public Response removeExercise(long exerciseId, User user){
        Optional<Exercise> exercise = exerciseRepository.findByIdAndUser(exerciseId, user);
        if(!exercise.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.EXERCISE_NOT_FOUND, null);

        entityManager.remove(exercise.get());

        return new Response(ResponseMessage.ExerciseResponseMessage.EXERCISE_REMOVED, null);
    }

    @Transactional
    public Response updateExercise(Exercise exercise, User user){
        Optional<Exercise> oldExercise = exerciseRepository.findByIdAndUser(exercise.getId(), user);
        if(!oldExercise.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.EXERCISE_NOT_FOUND, null);

        oldExercise.get().setName(exercise.getName());
        oldExercise.get().setGoal(exercise.getGoal());
        entityManager.merge(oldExercise.get());

        return new Response(ResponseMessage.ExerciseResponseMessage.EXERCISE_UPDATED, null);
    }
}
