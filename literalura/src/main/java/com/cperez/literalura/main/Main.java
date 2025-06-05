package com.cperez.literalura.main;


import com.cperez.literalura.repositories.BookRepository;
import com.cperez.literalura.services.ConsumoAPI;
import com.cperez.literalura.services.ConvierteDatos;

import java.util.Scanner;

public class Main {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/";
    private final ConvierteDatos conversor = new ConvierteDatos();
    private BookRepository bookRepository;

    public Main(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar series en servifor externo
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                    4 - Buscar serie en base de datos local
                    5 - Buscar Top 5 mejores series
                    6 - Buscar Series por Categoría
                    7 - Buscar por número de temporadas y evaluación:
                    8 - Buscar episodio por nombre: 
                    9 - Top 5 episodios por serie: 
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("hola");
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }


}
