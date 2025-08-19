package com.aluracursos.literalurachallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorData(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String fechaNaciemiento) {
}
