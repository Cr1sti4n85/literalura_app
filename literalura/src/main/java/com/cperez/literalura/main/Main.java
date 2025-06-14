package com.cperez.literalura.main;

import com.cperez.literalura.services.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
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
                    Bienvenidos. Escoge una opción:
                    
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar total de libros por idioma
                    6 - Listar libros por idioma
                   
                    0 - Salir
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1 -> buscarLibros();

                    case 2 ->listarLibros();

                    case 3 -> listarAutores();

                    case 4 -> listarAutoresVivos();

                    case 5 -> listarCantidadPorIdioma();

                    case 6 -> buscarPorIdioma();

                    case 0 -> cerrarAplicacion();

                    default -> System.out.println("Opción inválida");
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida");
                teclado.nextLine();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                teclado.nextLine();

            }

        }

    }

    private void buscarLibros() {
        bookService.buscarLibro(teclado);
    }

    private void listarLibros() {
        var pageNum = 0;
        var opt = 0;
        PageRequest pageRequest;

        while(opt != 2){
            //PAGINACION
            pageRequest = PageRequest.of(pageNum, 15);
            bookService.listarTodosLosLibros(pageRequest);
            System.out.print("""
                    Elige una opción:
                    1 - Obtener más resultados
                    2 - Volver al menú principal
                    """);
            opt = Integer.parseInt(teclado.nextLine());
            if (opt == 1) pageNum++;
        }

    }
    private void listarAutores() {
        bookService.listarTodosLosAutores();
    }

    private void listarAutoresVivos() {
        bookService.listarAutoresVivos(teclado);
    }

    private void listarCantidadPorIdioma() {
        bookService.mostrarCantidadLibrosPorIdioma();
    }

    private void buscarPorIdioma() {
        System.out.print("""
                Elige por código de idioma
                (Ejemplos: en (inglés), es (español), fr (francés)):
                """);
        var opcion = teclado.nextLine();
        bookService.buscarLibrosPorIdioma(opcion);
    }

    private void cerrarAplicacion() {
        System.out.println("Cerrando la aplicación...");
        int exitCode = SpringApplication.exit(context, () -> 0);
        System.exit(exitCode);
    }
}
