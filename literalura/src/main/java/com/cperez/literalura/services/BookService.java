package com.cperez.literalura.services;

import com.cperez.literalura.dtos.JsonResponseDTO;
import com.cperez.literalura.entities.Author;
import com.cperez.literalura.entities.Book;
import com.cperez.literalura.repositories.AuthorRepository;
import com.cperez.literalura.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();

    public void buscarLibro(Scanner cli){
        System.out.print("Escribe el nombre de la serie que deseas buscar: ");
        var bookName = cli.nextLine();
        var foundBooks = bookRepository.findByTitleContainsIgnoreCase(bookName);

        if (!foundBooks.isEmpty()){
            foundBooks.forEach(System.out::println);
            return;
        }

        buscarOnline(bookName);

    }

    public void listarTodosLosLibros(){
        var books = bookRepository.findAll();
        if(books.isEmpty()){
            System.out.println("No hay libros para listar");
            return;
        }
        books.forEach(System.out::println);
    }

    public void listarTodosLosAutores(){
        var authors = authorRepository.findAll();
        if(authors.isEmpty()){
            System.out.println("no hay autores para listar");
            return;
        }

        authors.forEach(System.out::println);
    }

    public void listarAutoresVivos(Scanner cli){
        System.out.println("Indica el año en el que el autor estaba vivo: ");
        var year = Integer.parseInt(cli.nextLine());
//        var authors = authorRepository.findByDeathYearGreaterThanAndBirthYearLessThan(year, year);
         var authors = authorRepository.findAuthorByYear(year);
        if(authors.isEmpty()){
            System.out.println("no hay autores para listar");
            return;
        }
        authors.forEach(System.out::println);
    }

    public void buscarLibrosPorIdioma(String language){
        List<Book> books = bookRepository.findByLanguage(language);
        if (books.isEmpty()){
            System.out.println("No se encontraron resultados");
            return;
        }
        books.forEach(System.out::println);
    }

    private void buscarOnline(String bookName){
        var json = consumoApi
                .obtenerDatos("https://gutendex.com/books/?search=" + bookName.replace(" ", "+"));

        var bookResults = conversor.obtenerDatos(json, JsonResponseDTO.class);

        if(bookResults.results().isEmpty()) {
            System.out.println("No se obtuvieron resultados");
            return;
        }
        mostrarYGuardarEnBD(bookResults);
    }

    private void mostrarYGuardarEnBD(JsonResponseDTO bookResults){
        Set<String> seenTitles = new HashSet<>();
        var bookList = bookResults.results().stream()
                .filter(bookDto -> seenTitles.add(bookDto.title()))
                .toList();

        Set<String> seenAuthorNames = new HashSet<>();
        var authorsList = bookList.stream()
                .flatMap(result -> result.author().stream()
                        .filter(authorDto -> seenAuthorNames.add(authorDto.name()))
                        .map(Author::new))
                .toList();

        for (Author author : authorsList) {
            String authorName = author.getName();

            bookList.stream()
                    .filter(bookDto -> !bookDto.author().isEmpty())
                    .filter(bookDto -> bookDto.author().getFirst().name().equals(authorName))
                    .forEach(bookDto -> {
                        var book = new Book(bookDto);
                        author.addBook(book);
                        System.out.println(book);
                    });

        }
        authorsList.stream().filter(a -> !a.getBooks().isEmpty())
                .forEach(authorRepository::save);
    }
}
