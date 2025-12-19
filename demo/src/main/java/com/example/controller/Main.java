package com.example.controller;

import com.example.model.Juego;
import com.example.model.BaseDatos;
import com.example.view.Interfaz;

public class Main {
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
        interfaz.probar(new Juego());

        BaseDatos bd = BaseDatos.getInstancia();        
    }
}