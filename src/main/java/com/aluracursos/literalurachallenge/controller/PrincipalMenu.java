package com.aluracursos.literalurachallenge.controller;

import com.aluracursos.literalurachallenge.model.DatosAutor;
import com.aluracursos.literalurachallenge.model.DatosLibro;
import com.aluracursos.literalurachallenge.model.Datos;
import com.aluracursos.literalurachallenge.model.Libro;
import com.aluracursos.literalurachallenge.service.ConsumoAPI;
import com.aluracursos.literalurachallenge.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class PrincipalMenu {
    private static final String URL_BASE = "http://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private List<Libro> libros = new ArrayList<>();
    private Set<Autor> autores = new HashSet<>();



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
                    muestraAutoresRegistrados();
                    break;
                case "4":
                    muestraAutoresVivosPorAnio();
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
            autores.addAll(libroEncontrado.getAutores().stream()
                    .map(d -> new Autor(d))
                    .collect(Collectors.toSet()));
        }else {
            System.out.println("Libro no encontrado");
        }
    }

    private void muestraLibrosRegistrados() {
        libros.stream()
                .forEach(l -> System.out.println(l.toString()));
    }

    private void muestraAutoresRegistrados() {
        autores.stream()
                .forEach(a -> System.out.println(a.toString()));
    }

    private void muestraAutoresVivosPorAnio() {
        System.out.println("Ingresa el año donde deseas buscar autores vivos: ");
        Integer anioBuscado = teclado.nextInt();
        teclado.nextLine();
        autores.stream()
                .filter(a -> a.getAnioMuerte() >= anioBuscado && a.getAnioNacimiento() <= anioBuscado)
                .peek(System.out::println)
                .forEach(a -> a.toString());

    }
}
