package com.cperez.literalura.services;

import com.cperez.literalura.dtos.JsonBookDTO;
import com.cperez.literalura.dtos.JsonResponseDTO;
import com.cperez.literalura.repositories.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();

    public void buscarLibro(){
        Scanner cli = new Scanner(System.in);

        System.out.print("Escribe el nombre de la serie que deseas buscar: ");
        var nombre = cli.nextLine();
        var json = consumoApi.obtenerDatos("https://gutendex.com/books/?search=" + nombre);
        System.out.println(json);

        var book = conversor.obtenerDatos(json, JsonResponseDTO.class);
        System.out.println(book);

    }

}
