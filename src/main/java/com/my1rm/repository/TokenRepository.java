package com.my1rm.repository;

import com.my1rm.model.database.Token;
import com.my1rm.model.database.User;
import com.my1rm.model.types.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByUserAndHash(User user, String hash);

    Optional<Token> findByHashAndType(String hash, TokenType tokenType);

    void deleteAllByUserAndType(User user, TokenType tokenType);
}
