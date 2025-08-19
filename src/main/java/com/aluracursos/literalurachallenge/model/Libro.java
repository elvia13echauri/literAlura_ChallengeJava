package com.aluracursos.literalurachallenge.model;

import java.util.List;

public class Libro {

    private String id;
    private String titulo;
    private List<DatosAutor> autores;
    private List<String> idiomas;
    private Double numeroDescargas;

    public Libro(DatosLibro data){
        this.id = data.id();
        this.titulo = data.titulo();
        this.autores = data.autores();
        this.idiomas = data.idiomas();
        this.numeroDescargas = data.numeroDescargas();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DatosAutor> getAutores() {
        return autores;
    }

    public void setAutores(List<DatosAutor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "Titulo: '" + titulo + '\'' +
                ", Autores: " + autores +
                ", Idiomas: " + idiomas +
                ", Numero de Descargas: " + numeroDescargas;
    }
}
