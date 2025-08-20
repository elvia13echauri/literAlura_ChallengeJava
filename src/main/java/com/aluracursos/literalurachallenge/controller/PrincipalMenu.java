package com.aluracursos.literalurachallenge.controller;

import com.aluracursos.literalurachallenge.model.*;
import com.aluracursos.literalurachallenge.repository.AutorRepository;
import com.aluracursos.literalurachallenge.repository.LibroRepository;
import com.aluracursos.literalurachallenge.service.ConsumoAPI;
import com.aluracursos.literalurachallenge.service.ConvierteDatos;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.util.*;
import java.util.stream.Collectors;

public class PrincipalMenu {
    private static final String URL_BASE = "http://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private List<Libro> libros = new ArrayList<>();
    private Set<Autor> autores = new HashSet<>();
    private LibroRepository repositoryL;
    private AutorRepository repositoryA;
    Optional<DatosLibro> libroBuscado;



    public void muestraElMenu(LibroRepository repositorioLibro, AutorRepository repositorioAutor) {
        this.repositoryL = repositorioLibro;
        this.repositoryA = repositorioAutor;
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

    private Datos getDatosLibro(String nombreLibro){
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.toLowerCase().replace(" ","+"));
        System.out.println(json);
        Datos datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        return datosBusqueda;
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine().toLowerCase();
        Datos datos = getDatosLibro(nombreLibro);
        libroBuscado = datos.results().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()){
            System.out.println("Libro encontrado: ");
            Libro libroEncontrado = new Libro(libroBuscado.get());
            DatosAutor dataAuxiliar = new DatosAutor(libroBuscado.get().autores().get(0).nombre(),
                    libroBuscado.get().autores().get(0).fechaNaciemiento(),
                    libroBuscado.get().autores().get(0).fechaMuerte());
            Autor autorFinal = repositoryA.findByNombre(dataAuxiliar.nombre())
                    .orElseGet(()-> repositoryA.save(new Autor(dataAuxiliar)));
            libroEncontrado.setAutor(autorFinal);
            System.out.println("este autor se guardo:" + libroEncontrado.getAutor());
            repositoryL.save(libroEncontrado);
            System.out.println(libroEncontrado.toString());
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