package com.aluracursos.literalurachallenge.controller;

import com.aluracursos.literalurachallenge.model.Data;
import com.aluracursos.literalurachallenge.service.ConsumoAPI;
import com.aluracursos.literalurachallenge.service.ConvierteDatos;

public class PrincipalMenu {
    private static final String URL_BASE = "http://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu() {
        var json = consumoAPI.obtenerDatos(URL_BASE);
        Data data = conversor.obtenerDatos(json, Data.class);
        System.out.println(data);
    }
}
