package com.cperez.literalura.repositories;

import com.cperez.literalura.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainsIgnoreCase(String title);

    List<Book> findByLanguage(String language);
}
