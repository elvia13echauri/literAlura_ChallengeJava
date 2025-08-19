package com.aluracursos.literalurachallenge;

import com.aluracursos.literalurachallenge.controller.PrincipalMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteralurachallengeApplication implements CommandLineRunner {

	public static void main(String[] args) {
        SpringApplication.run(LiteralurachallengeApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        PrincipalMenu menu = new PrincipalMenu();
        menu.muestraElMenu();
    }
}
