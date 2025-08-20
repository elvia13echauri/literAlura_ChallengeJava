package com.aluracursos.literalurachallenge.model;

import com.aluracursos.literalurachallenge.model.DatosAutor;
import jakarta.persistence.*;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autor_id;
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<>();

    public Autor(){}

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