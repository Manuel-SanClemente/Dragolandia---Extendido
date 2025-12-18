package com.example.controller;

import com.example.model.Juego;
import com.example.model.baseDatos;
import com.example.view.Interfaz;

public class Main {
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
        interfaz.probar(new Juego());

        baseDatos bd = new baseDatos();        
        bd.engadirEstructura(interfaz.getMago(), interfaz.getBosque(), interfaz.getMonstruoJefe(), interfaz.getMago().getHechizos(), interfaz.getBosque().getDragon());
    }
}