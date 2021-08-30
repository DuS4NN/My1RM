package com.my1rm.repository;

import com.my1rm.model.database.Season;
import com.my1rm.model.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long>{

    Optional<Season> findByNameAndUser(String name, User user);

    Optional<Season> findByIdAndUser(long id, User user);

    int countAllByUser(User user);
}
