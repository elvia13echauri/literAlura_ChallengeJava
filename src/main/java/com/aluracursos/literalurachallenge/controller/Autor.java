package com.aluracursos.literalurachallenge.controller;

import com.aluracursos.literalurachallenge.model.DatosAutor;

import java.time.Year;

public class Autor {
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;

    public Autor(DatosAutor datos){
        this.nombre = datos.nombre();
        this.anioNacimiento = datos.fechaNaciemiento();
        this.anioMuerte = datos.fechaMuerte();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(Integer anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    @Override
    public String toString() {
        return  "Nombre: '" + nombre + '\'' +
                ", Año de Nacimiento: " + anioNacimiento +
                ", Año de Muerte: " + anioMuerte;
    }
}
