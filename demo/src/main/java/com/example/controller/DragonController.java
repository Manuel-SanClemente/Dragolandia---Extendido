package com.example.controller;

import com.example.model.Dragon;
import com.example.model.HibernateUtil;

import jakarta.persistence.EntityManager;

/**
 * Controlador de la clase Dragon. Aplica el formato Singleton 
 */
public class DragonController {
    private static DragonController instancia;

    private DragonController() {
        System.out.println("\nControlador de Dragon inicializado\n");
    }

    /**
     * Devuelve la instancia del controlador
     * @return la instancia de la clase
     */
    public static DragonController getInstancia() {
        if (instancia == null) {
            instancia = new DragonController();
        }
        return instancia;
    }

    /**
     * Añade la clase a la base de datos
     * @param dragon
     */
    public void engadirDragon(Dragon dragon) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(dragon);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    /**
     * Busca y, si la encuentra, imprime la entidad correspondiente de la base de datos
     * @param dragon
     */
    public void buscarDragon(Dragon dragon) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            Dragon d = em.find(Dragon.class, dragon.getId());
            System.out.println(d);
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Borra la entidad correspondiente
     * @param dragon
     */
    public void borrarDragon(Dragon dragon) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(dragon);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    /**
     * Modifica la entidad con los datos de la otra clase.
     * Se le deberan pasar dos datos a la Base de Datos: la entidad original, más la versión modificada de la misma.
     * Con ambas, se aplican las modificaciones de la modificada a la original, y las unimos en la BD
     * @param dragon
     * @param d
     */
    public void modificarDragon(Dragon dragon, Dragon d) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            dragon.setIntensidadFuego(d.getIntensidadFuego());
            dragon.setNombre(d.getNombre());
            dragon.setResistencia(d.getResistencia());

            em.getTransaction().begin();
            em.merge(dragon);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
