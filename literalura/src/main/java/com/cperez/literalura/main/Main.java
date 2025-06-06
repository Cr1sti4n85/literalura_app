package com.cperez.literalura.main;

import com.cperez.literalura.services.BookService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Main {
    private final Scanner teclado = new Scanner(System.in);
    private final BookService bookService;

    public Main(BookService bs) {
        this.bookService = bs;
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
                case 1:
                    buscarLibros();
                    break;

                case 2:
                    listarLibros();
                    break;

                case 3:
                    listarAutores();
                    break;

                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    buscarPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
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
            case "1":
                bookService.buscarLibrosPorIdioma("es");
                break;
            case "2":
                bookService.buscarLibrosPorIdioma("en");
                break;
            case "3":
                bookService.buscarLibrosPorIdioma("fr");
                break;
            case "4":
                bookService.buscarLibrosPorIdioma("pt");
                break;
            default:
                System.out.println("Entrada no válida");
        }
    }
}
