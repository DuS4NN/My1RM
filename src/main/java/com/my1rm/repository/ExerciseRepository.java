package com.my1rm.repository;

import com.my1rm.model.database.Exercise;
import com.my1rm.model.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Optional<Exercise> findByNameAndUser(String name, User user);

    Optional<Exercise> findByIdAndUser(long id, User user);

    Optional<Exercise> findFirstByUser(User user);

    int countAllByUser(User user);
}
