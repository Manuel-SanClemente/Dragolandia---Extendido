package com.example.model;

import java.util.List;

import com.example.controller.BosqueController;
import com.example.controller.DragonController;
import com.example.controller.HechizoController;
import com.example.controller.MagoController;
import com.example.controller.MonstruoController;


public class BaseDatos {
    private static BaseDatos instancia;
    private BosqueController bosqueController = BosqueController.getInstancia();
    private DragonController dragonController = DragonController.getInstancia();
    private HechizoController hechizoController = HechizoController.getInstancia();
    private MagoController magoController = MagoController.getInstancia();
    private MonstruoController monstruoController = MonstruoController.getInstancia();

    private BaseDatos() {
        System.out.println("\nIniciando conexión con Base de datos \n");
    }

    public static BaseDatos getInstancia() {
        if (instancia == null) {
            instancia = new BaseDatos();
        }
        return instancia;
    }

    public void engadirMagoYHechizos(Mago mago){
        List<Hechizo> hechizos = mago.getHechizos();
        for (Hechizo hechizo : hechizos) {
            hechizoController.engadirHechizo(hechizo);
        }

        magoController.engadirMago(mago);
    }

    public void engadirBosqueYDemas(Bosque bosque) {
        List<Monstruo> monstruos = bosque.getListaMonstruos();
        Dragon dragon = bosque.getDragon();

        for (Monstruo monstruo : monstruos) {
            monstruoController.engadirMonstruo(monstruo);
        }

        dragonController.engadirDragon(dragon);

        bosqueController.engadirBosque(bosque);
    }

}
