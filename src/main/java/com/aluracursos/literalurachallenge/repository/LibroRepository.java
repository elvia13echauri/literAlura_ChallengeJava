package com.aluracursos.literalurachallenge.repository;

import com.aluracursos.literalurachallenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository  extends JpaRepository<Libro,Long> {

}
