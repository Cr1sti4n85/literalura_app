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
public class LiteraluraApplication  {

	//INYECCION DE DEPENDENCIA
//	@Autowired
//	private BookRepository repository;

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LiteraluraApplication.class, args);

		var mainService = context.getBean(Main.class);
		mainService.muestraElMenu();

	}

}
