package com.aluracursos.literalurachallenge.controller;

import com.aluracursos.literalurachallenge.model.DatosLibro;
import com.aluracursos.literalurachallenge.model.Datos;
import com.aluracursos.literalurachallenge.model.Libro;
import com.aluracursos.literalurachallenge.service.ConsumoAPI;
import com.aluracursos.literalurachallenge.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PrincipalMenu {
    private static final String URL_BASE = "http://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private List<Libro> libros = new ArrayList<>();



    public void muestraElMenu() {
        String opcion = "-1";
        while (!opcion.equals("0")) {
            var menu = """
                    1 - Buscar libro por Titulo
                    2 - Lista de Libros registrados
                    3 - Listar Autores registrados
                    4 - Listar autores vivos en determinado año
                    5 - listar libros por idiomas
                    6 -            
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    buscarLibroPorTitulo();
                    break;
                case "2":
                    muestraLibrosRegistrados();
                    break;
                case "3":
                    muestraLibrosRegistrados();
                    break;
                case "4":
                    muestraLibrosRegistrados();
                    break;
                case "5":
                    muestraLibrosRegistrados();
                    break;
                
                case "0":
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }

    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingresa el nombre del libro que deseas buscar:");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.results().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()){
            System.out.println("Libro encontrado: ");
            Libro libroEncontrado = new Libro(libroBuscado.get());
            libros.add(libroEncontrado);
            System.out.println(libroEncontrado.toString());
        }else {
            System.out.println("Libro no encontrado");
        }
    }

    private void muestraLibrosRegistrados() {
        libros.stream()
                .forEach(l -> System.out.println(l.toString()));
    }
}
