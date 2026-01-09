package com.example.view;

import java.util.Scanner;

import com.example.model.*;

public class Interfaz {
    private BaseDatos bd;
    private Juego juego;

    public Interfaz(){
        bd = BaseDatos.getInstancia();
        juego = new Juego();
    }

    public void comenzar(){
        System.out.println("A continuación, escribe los datos de tu mago.\n");

        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe el nombre del mago: ");
        String nombre = sc.nextLine();

        System.out.println("Escribe la vida del mago: ");
        Integer vida = sc.nextInt();

        System.out.println("Escribe la mana del mago: ");
        Integer mana = sc.nextInt();

        sc.close();

        Bosque bosque = juego.generarEstructuras();
        Mago mago = juego.nuevoMago(nombre, vida, mana);

        bd.engadirMagoYHechizos(mago);
        bd.engadirBosqueYDemas(bosque);
    }
}
