package com.my1rm.service;

import com.my1rm.api.Tables;
import com.my1rm.jooq.tables.Attempt;
import com.my1rm.model.Response;
import com.my1rm.model.ResponseMessage;
import com.my1rm.model.database.Season;
import com.my1rm.model.database.User;
import com.my1rm.model.pojo.SeasonPojo.GetAllSeasonsPOJO;
import com.my1rm.repository.AttemptRepository;
import com.my1rm.repository.SeasonRepository;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SeasonService {

    private SeasonRepository seasonRepository;
    private AttemptRepository attemptRepository;
    private DSLContext dslContext;
    private EntityManager entityManager;
    private Tables tables;

    public Response getAllSeasons(long userId, String orderBy){

        Table<?> firstAndLastAttemptInSeason = tables.getFirstAndLastAttemptInSeason(userId);

        //Order by
        SortField[] sortField = {com.my1rm.jooq.tables.Season.SEASON.CREATED_AT.desc()};
        if(orderBy.contains("NAME")){
            sortField = new SortField[]{com.my1rm.jooq.tables.Season.SEASON.NAME.asc()};
        }

        List<GetAllSeasonsPOJO> result = dslContext
            .select(
                com.my1rm.jooq.tables.Season.SEASON.ID.as("seasonId"),
                com.my1rm.jooq.tables.Season.SEASON.COLOR.as("seasonColor"),
                com.my1rm.jooq.tables.Season.SEASON.NAME.as("seasonName"),
                com.my1rm.jooq.tables.Season.SEASON.CREATED_AT.as("seasonCreatedAt"),
                firstAndLastAttemptInSeason.field("firstDate").as("firstAttempt"),
                firstAndLastAttemptInSeason.field("lastDate").as("lastAttempt"))
            .from(com.my1rm.jooq.tables.Season.SEASON)
            .leftJoin(firstAndLastAttemptInSeason)
                .on(com.my1rm.jooq.tables.Season.SEASON.ID.eq(DSL.coerce(firstAndLastAttemptInSeason.field("seasonId"), Long.class)))
            .where(com.my1rm.jooq.tables.Season.SEASON.USER_ID.eq(userId))
            .orderBy(sortField)
            .fetchInto(GetAllSeasonsPOJO.class);

        return new Response(ResponseMessage.CommonResponseMessage.SUCCESS, result);
    }

    @Transactional
    public Response addSeason(Season season, User user){
        Optional<Season> maybeSeason = seasonRepository.findByNameAndUser(season.getName(), user);
        if(maybeSeason.isPresent()) return new Response(ResponseMessage.SeasonResponseMessage.NAME_ALREADY_EXISTS, null);

        if(seasonRepository.countAllByUser(user) >= 30) return new Response(ResponseMessage.SeasonResponseMessage.MAXIMUM_NUMBER_OF_SEASONS,null);

        Season newSeason = new Season();
        newSeason.setName(season.getName());
        newSeason.setColor(season.getColor());
        newSeason.setUser(user);
        entityManager.persist(newSeason);

        HashMap<String, Object> result = new HashMap<>();
        result.put("seasonId", newSeason.getId());
        result.put("seasonColor", newSeason.getColor());
        result.put("seasonName", newSeason.getName());
        result.put("seasonCreatedAt", new Date());
        result.put("firstAttempt", null);
        result.put("lastAttempt", null);

        return new Response(ResponseMessage.SeasonResponseMessage.SEASON_CREATED, result);
    }

    @Transactional
    public Response removeSeason(long seasonId, User user, long replaceBy){
        Optional<Season> seasonToRemove = seasonRepository.findByIdAndUser(seasonId, user);
        if(!seasonToRemove.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.SEASON_NOT_FOUND, null);

        //Delete all attempts from season
        if(replaceBy == 0){
            attemptRepository.deleteAllBySeasonAndUser(seasonToRemove.get(), user);
        //Replace removed season in attempts
        }else{
            Optional<Season> seasonToReplace = seasonRepository.findByIdAndUser(replaceBy, user);
            if(!seasonToReplace.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.SEASON_NOT_FOUND, null);

            dslContext.update(Attempt.ATTEMPT)
                .set(Attempt.ATTEMPT.SEASON_ID, replaceBy)
                .where(Attempt.ATTEMPT.SEASON_ID.eq(seasonId))
                .and(Attempt.ATTEMPT.USER_ID.eq(user.getId()))
                .execute();
        }
        entityManager.remove(seasonToRemove.get());

        return new Response(ResponseMessage.SeasonResponseMessage.SEASON_REMOVED, null);
    }

    @Transactional
    public Response updateSeason(Season season, User user){
        Optional<Season> oldSeason = seasonRepository.findByIdAndUser(season.getId(), user);
        if(!oldSeason.isPresent()) return new Response(ResponseMessage.CommonResponseMessage.SEASON_NOT_FOUND, null);

        oldSeason.get().setName(season.getName());
        oldSeason.get().setColor(season.getColor());
        entityManager.merge(oldSeason.get());

        return new Response(ResponseMessage.SeasonResponseMessage.SEASON_UPDATED, null);
    }
}
