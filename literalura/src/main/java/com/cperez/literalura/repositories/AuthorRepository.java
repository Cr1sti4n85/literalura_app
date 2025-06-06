package com.cperez.literalura.repositories;

import com.cperez.literalura.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByDeathYearGreaterThanAndBirthYearLessThan(int birthYear, int deathYear);
}
