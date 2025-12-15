package com.example.view;

import java.util.Scanner;

import com.example.model.Bosque;
import com.example.model.Juego;
import com.example.model.Mago;
import com.example.model.Monstruo;

public class Interfaz {
    private Mago mago;
    private Bosque bosque;
    private Monstruo monstruoJefe;

    public Interfaz(){}

    public Mago getMago() {
        return mago;
    }

    public void setMago(Mago mago) {
        this.mago = mago;
    }

    public Bosque getBosque() {
        return bosque;
    }

    public void setBosque(Bosque bosque) {
        this.bosque = bosque;
    }

    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    public void probar(Juego juego){
        System.out.println("A continuación, escribe los datos de tu mago.\n");

        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe el nombre del mago: ");
        String nombre = sc.nextLine();

        System.out.println("Escribe la vida del mago: ");
        Integer vida = sc.nextInt();

        System.out.println("Escribe la mana del mago: ");
        Integer mana = sc.nextInt();

        sc.close();

        this.bosque = juego.generarEstructuras();
        this.monstruoJefe = bosque.getMonstruoJefe();
        this.mago = juego.nuevoMago(nombre, vida, mana);

        System.out.println("Bosque, Monstruo jefe y Mago: ");
        
        System.out.println(bosque);
        System.out.println(bosque.getMonstruoJefe());
        System.out.println(mago);
    }
}
