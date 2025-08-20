package com.aluracursos.literalurachallenge.repository;

import com.aluracursos.literalurachallenge.model.Autor;
import com.aluracursos.literalurachallenge.model.DatosAutor;
import com.aluracursos.literalurachallenge.model.Libro;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombre(String nombreAutor);
}
