package com.cperez.literalura.repositories;

import com.cperez.literalura.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
