package com.cperez.literalura.repositories;

import com.cperez.literalura.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByDeathYearGreaterThanAndBirthYearLessThan(int birthYear, int deathYear);

    @Query("SELECT a FROM Author a WHERE a.birthYear < :year AND a.deathYear > :year")
    List<Author> findAuthorByYear(int year);

    Optional<Author> findByName(String name);
}
