package com.cperez.literalura;

import com.cperez.literalura.main.Main;
import com.cperez.literalura.repositories.BookRepository;
import com.cperez.literalura.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	//INYECCION DE DEPENDENCIA
	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {

		SpringApplication.run(LiteraluraApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repository);
		main.muestraElMenu();

	}
}
