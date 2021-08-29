package com.my1rm.repository;

import com.my1rm.model.database.User;
import com.my1rm.model.database.UserOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserOptionsRepository extends JpaRepository<UserOption, Long> {

    Optional<UserOption> findByUser(User user);
}
