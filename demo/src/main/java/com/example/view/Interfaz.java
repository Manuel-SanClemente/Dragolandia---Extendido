package com.example.view;

import java.util.Scanner;
import com.example.model.*;

/**
 * Clase para encargarse de la interacción y inicialización del juego.
 */
public class Interfaz {
    private BaseDatos bd;
    private Generador juego;

    public Interfaz(){
        bd = BaseDatos.getInstancia();
        juego = new Generador();
    }

    /**
     * Metodo comenzar. Esta clase genera las estructuras necesarias para el juego, a la vez que le pide al usuario que cree a su mago.
     * Una vez creadas, se añaden a la BD
     */
    public void comenzar(){
        System.out.println("A continuación, escribe los datos de tu mago.\n");

        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe el nombre del mago: ");
        String nombre = sc.nextLine();

        System.out.println("Escribe la vida del mago: ");
        Integer vida = sc.nextInt();

        while (vida <= 0) {
            System.out.println("La vida del mago no debería ser ni 0 ni negativo. Por favor, escribe la vida del mago: ");
            vida = sc.nextInt();            
        }

        System.out.println("Escribe la mana del mago: ");
        Integer mana = sc.nextInt();

        while (mana <= 0) {
            System.out.println("La mana del mago no debería ser ni 0 ni negativo. Por favor, escribe la mana del mago: ");
            mana = sc.nextInt();            
        }

        sc.close();

        Bosque bosque = juego.generarEstructuras();
        Mago mago = juego.nuevoMago(nombre, vida, mana);

        bd.engadirMagoYHechizos(mago);
        bd.engadirBosqueYDemas(bosque);
        jugar(mago, bosque);
    }

    /**
     * Metodo jugar. Esta clase se encarga del manejo del juego.
     * En ella, el mago y el monstruo jefe se atacan por turnos hasta que caiga uno.
     * Cuando uno de ellos caiga, se presentan los resultados y se borra al perdedor
     * @param mago
     * @param bosque
     */
    public void jugar(Mago mago, Bosque bosque){

        Monstruo jefe = bosque.getMonstruoJefe();

        System.out.println("Comienza la batalla! Te enfrentas a "+ jefe.getNombre());

        while (mago.getVida() !=0 | jefe.getVida()!=0) {
            mago.lanzarHechizo(jefe);
            while (jefe.getVida()!=0) {
                jefe.atacarMago(mago);                
            }         
        }

        System.out.println("Ha acabado la partida");

        if (mago.getVida() <=0) {
            System.out.println("HAS PERDIDO");
        }

        if (jefe.getVida() <=0 ) {
            System.out.println("HAS GANADO");
        }
        
        bd.comprobarParticipantes(mago, jefe);
    }
}
