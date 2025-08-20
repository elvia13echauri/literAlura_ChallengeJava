package com.aluracursos.literalurachallenge;

import com.aluracursos.literalurachallenge.controller.PrincipalMenu;
import com.aluracursos.literalurachallenge.repository.AutorRepository;
import com.aluracursos.literalurachallenge.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteralurachallengeApplication implements CommandLineRunner {

    @Autowired
    private LibroRepository repositorioLibro;

    @Autowired
    private AutorRepository repositorioAutor;

    public static void main(String[] args) {
        SpringApplication.run(LiteralurachallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PrincipalMenu menu = new PrincipalMenu();
        menu.muestraElMenu(repositorioLibro,repositorioAutor);
    }
}