package com.cperez.literalura.main;

import com.cperez.literalura.services.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Main {
    private final Scanner teclado = new Scanner(System.in);
    private final BookService bookService;
    private final ApplicationContext context;

    public Main(BookService bs, ApplicationContext context) {
        this.bookService = bs;
        this.context = context;
    }


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                   
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarLibros();

                case 2 ->listarLibros();

                case 3 -> listarAutores();

                case 4 -> listarAutoresVivos();

                case 5 -> buscarPorIdioma();

                case 0 -> cerrarAplicacion();

                default -> System.out.println("Opción inválida");
            }
        }

    }


    private void buscarLibros() {
        bookService.buscarLibro(teclado);
    }

    private void listarLibros() {
       bookService.listarTodosLosLibros();
    }
    private void listarAutores() {
        bookService.listarTodosLosAutores();
    }

    private void listarAutoresVivos() {
        bookService.listarAutoresVivos(teclado);
    }

    private void buscarPorIdioma() {
        System.out.print("""
                Elige un idioma:
                1- Español
                2- Inglés
                3- Francés
                4- Portugués
                """);
        var opcion = teclado.nextLine();
        switch (opcion){
            case "1" -> bookService.buscarLibrosPorIdioma("es");

            case "2" -> bookService.buscarLibrosPorIdioma("en");

            case "3" -> bookService.buscarLibrosPorIdioma("fr");

            case "4" -> bookService.buscarLibrosPorIdioma("pt");

            default -> System.out.println("Entrada no válida");
        }
    }

    private void cerrarAplicacion() {
        System.out.println("Cerrando la aplicación...");
        int exitCode = SpringApplication.exit(context, () -> 0);
        System.exit(exitCode);
    }
}
