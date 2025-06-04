package com.cperez.literalura.repositories;

import com.cperez.literalura.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
