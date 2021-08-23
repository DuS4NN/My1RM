package com.my1rm.repository;

import com.my1rm.model.database.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    Language findFirstByOrderById();
}
