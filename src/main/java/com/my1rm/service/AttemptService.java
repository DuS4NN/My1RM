package com.my1rm.service;

import com.my1rm.api.API;
import com.my1rm.model.Response;
import com.my1rm.model.ResponseMessage;
import com.my1rm.model.database.Attempt;
import com.my1rm.model.database.Exercise;
import com.my1rm.model.database.Season;
import com.my1rm.model.database.User;
import com.my1rm.model.pojo.AttemptPojo.GetAttemptsPOJO;
import com.my1rm.repository.AttemptRepository;
import com.my1rm.repository.ExerciseRepository;
import com.my1rm.repository.SeasonRepository;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AttemptService {

    private AttemptRepository attemptRepository;
    private SeasonRepository seasonRepository;
    private ExerciseRepository exerciseRepository;
    private DSLContext dslContext;
    private EntityManager entityManager;

    public Response getAttempts(long userId, long exerciseId, int page){
        List<GetAttemptsPOJO> result = dslContext
            .select(
                com.my1rm.jooq.tables.Attempt.ATTEMPT.ID.as("attemptId"),
                com.my1rm.jooq.tables.Attempt.ATTEMPT.REPETITIONS.as("attemptRepetitions"),
                com.my1rm.jooq.tables.Attempt.ATTEMPT.SUCCESS.as("attemptSuccess"),
                com.my1rm.jooq.tables.Attempt.ATTEMPT.WEIGHT.as("attemptWeight"),
                com.my1rm.jooq.tables.Attempt.ATTEMPT.DATE.as("attemptDate"),
                com.my1rm.jooq.tables.Season.SEASON.NAME.as("seasonName"),
                com.my1rm.jooq.tables.Season.SEASON.COLOR.as("seasonColor"))
            .from(com.my1rm.jooq.tables.Attempt.ATTEMPT)
            .leftJoin(com.my1rm.jooq.tables.Season.SEASON)
                .on(com.my1rm.jooq.tables.Season.SEASON.ID.eq(com.my1rm.jooq.tables.Attempt.ATTEMPT.SEASON_ID))
            .where(com.my1rm.jooq.tables.Attempt.ATTEMPT.USER_ID.eq(userId))
            .and(com.my1rm.jooq.tables.Attempt.ATTEMPT.EXERCISE_ID.eq(exerciseId))
            .orderBy(com.my1rm.jooq.tables.Attempt.ATTEMPT.DATE.desc())
            .limit(API.ITEMS_ON_PAGE)
            .offset((page-1)*API.ITEMS_ON_PAGE)
            .fetchInto(GetAttemptsPOJO.class);

        return new Response(ResponseMessage.CommonResponseMessage.SUCCESS, result);
    }

    @Transactional
    public Response addAttempt(User user, Attempt attempt, long exerciseId, long seasonId){
        Optional<Exercise> exercise = exerciseRepository.findByIdAndUser(exerciseId, user);
        if(!exercise.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.EXERCISE_NOT_FOUND, null);

        Optional<Season> season = seasonRepository.findByIdAndUser(seasonId, user);
        if(!season.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.SEASON_NOT_FOUND, null);

        Attempt newAttempt = new Attempt();
        newAttempt.setRepetitions(attempt.getRepetitions());
        newAttempt.setSuccess(attempt.isSuccess());
        newAttempt.setWeight(attempt.getWeight());
        newAttempt.setDate(attempt.getDate());
        newAttempt.setUser(user);
        newAttempt.setExercise(exercise.get());
        newAttempt.setSeason(season.get());
        entityManager.persist(newAttempt);

        HashMap<String, Object> result = new HashMap<>();
        result.put("attemptId", newAttempt.getId());
        result.put("attemptRepetitions", newAttempt.getRepetitions());
        result.put("attemptSuccess", newAttempt.isSuccess());
        result.put("attemptWeight", newAttempt.getWeight());
        result.put("attemptDate", newAttempt.getDate());
        result.put("seasonName", season.get().getName());
        result.put("seasonColor", season.get().getColor());

        return new Response(ResponseMessage.AttemptResponseMessage.ATTEMPT_CREATED, result);
    }

    @Transactional
    public Response removeAttempt(User user, long attemptId){
        Optional<Attempt> attempt = attemptRepository.findByUserAndId(user, attemptId);
        if(!attempt.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.ATTEMPT_NOT_FIND, null);

        entityManager.remove(attempt.get());

        return new Response(ResponseMessage.AttemptResponseMessage.ATTEMPT_REMOVED, null);
    }
}
