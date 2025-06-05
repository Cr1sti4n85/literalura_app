package com.cperez.literalura.services;

import com.cperez.literalura.dtos.JsonBookDTO;
import com.cperez.literalura.dtos.JsonResponseDTO;
import com.cperez.literalura.entities.Author;
import com.cperez.literalura.entities.Book;
import com.cperez.literalura.repositories.AuthorRepository;
import com.cperez.literalura.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();

    public void buscarLibro(Scanner cli){

        System.out.print("Escribe el nombre de la serie que deseas buscar: ");
        var nombre = cli.nextLine();
        var json = consumoApi.obtenerDatos("https://gutendex.com/books/?search=" + nombre);

        var bookResults = conversor.obtenerDatos(json, JsonResponseDTO.class);
        Set<String> seenTitles = new HashSet<>();
        var bookList = bookResults.results().stream()
                        .filter(bookDto -> seenTitles.add(bookDto.title()))
                        .toList();

        Set<String> seenAuthorNames = new HashSet<>();
        var authorsList = bookList.stream()
                .flatMap(result -> result.author().stream()
                        .filter(authorDto -> seenAuthorNames.add(authorDto.name()))
                        .map(authorDto -> new Author(authorDto)))
                .toList();


        for (Author author : authorsList) {
            String authorName = author.getName();

            bookList.stream()
                    .filter(bookDto -> !bookDto.author().isEmpty())
                    .filter(bookDto -> bookDto.author().getFirst().name().equals(authorName))
                    .forEach(bookDto -> {
                        var book = new Book(bookDto);
                        author.addBook(book);
                    });

        }
        authorsList.stream().filter(a -> !a.getBooks().isEmpty())
                .forEach(authorRepository::save);

    }

}
