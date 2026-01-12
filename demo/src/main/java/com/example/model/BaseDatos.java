package com.example.model;

import java.util.List;

import com.example.controller.BosqueController;
import com.example.controller.DragonController;
import com.example.controller.HechizoController;
import com.example.controller.MagoController;
import com.example.controller.MonstruoController;


/**
 * Clase BaseDatos. Sirve como intermidiaria para comunicarse entre los controladores de otras clases, al igual que ciertos metodos especiales.
 * Aplica el formato Singleton
 */

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

    /**
     * Devuelve la instancia del controlador
     * @return la instancia de la clase
     */
    public static BaseDatos getInstancia() {
        if (instancia == null) {
            instancia = new BaseDatos();
        }
        return instancia;
    }

    /**
     * Añade mago y hechizos. Para ello, simplemente se obtiene la lista de hechizos del mago, y se añaden todos en un bucle individualmente
     * @param mago
     */
    public void engadirMagoYHechizos(Mago mago){
        List<Hechizo> hechizos = mago.getHechizos();
        for (Hechizo hechizo : hechizos) {
            hechizoController.engadirHechizo(hechizo);
        }

        magoController.engadirMago(mago);
    }

    /**
     * Añade un bosque, monstruos y dragon a la BD. Para ello, se obtienen la lista de monstruos y el dragon del bosque y se añaden a la base de datos.
     * @param bosque
     */
    public void engadirBosqueYDemas(Bosque bosque) {
        List<Monstruo> monstruos = bosque.getListaMonstruos();
        Dragon dragon = bosque.getDragon();

        for (Monstruo monstruo : monstruos) {
            monstruoController.engadirMonstruo(monstruo);
        }

        dragonController.engadirDragon(dragon);

        bosqueController.engadirBosque(bosque);
    }

    /**
     * Para comprobar los resultados de una batalla. Se comprueba la vida de ambos participantes, y dependiendo de su valor, se borra uno de la BD
     * @param mago
     * @param monstruo
     */
    public void comprobarParticipantes(Mago mago, Monstruo monstruo){
        if (mago.getVida() <=0) {
            magoController.borrarMago(mago);
        }
        if (monstruo.getVida() <=0) {
            monstruoController.borrarMonstruo(monstruo);
        }
    }

}
