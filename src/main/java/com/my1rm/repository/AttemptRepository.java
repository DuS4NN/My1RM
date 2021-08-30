package com.my1rm.repository;

import com.my1rm.model.database.Attempt;
import com.my1rm.model.database.Season;
import com.my1rm.model.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long> {

    void deleteAllBySeasonAndUser(Season season, User user);
}
