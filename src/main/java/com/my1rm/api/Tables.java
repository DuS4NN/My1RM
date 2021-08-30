package com.my1rm.api;

import com.my1rm.jooq.tables.Attempt;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class Tables {

    private DSLContext dslContext;

    public Table getBestAttemptsTable(long userId){
        return dslContext
            .select(
                Attempt.ATTEMPT.EXERCISE_ID.as("exerciseId"),
                DSL.max(Attempt.ATTEMPT.WEIGHT).as("bestAttempt"))
            .from(Attempt.ATTEMPT)
            .where(Attempt.ATTEMPT.USER_ID.eq(userId))
            .and(Attempt.ATTEMPT.SUCCESS.isTrue())
            .groupBy(Attempt.ATTEMPT.EXERCISE_ID)
            .asTable("bestUserAttempts");
    }

    public Table getFirstAndLastAttemptInSeason(long userId){
        return dslContext
            .select(
                DSL.min(Attempt.ATTEMPT.DATE).as("firstDate"),
                DSL.max(Attempt.ATTEMPT.DATE).as("lastDate"),
                Attempt.ATTEMPT.SEASON_ID.as("seasonId"))
            .from(Attempt.ATTEMPT)
            .where(Attempt.ATTEMPT.USER_ID.eq(userId))
            .groupBy(Attempt.ATTEMPT.SEASON_ID)
            .asTable("firstAndLastAttemptInSeason");
    }
}