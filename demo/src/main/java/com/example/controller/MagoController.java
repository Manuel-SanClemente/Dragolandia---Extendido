package com.example.controller;

import com.example.HibernateUtil;
import com.example.model.Mago;

import jakarta.persistence.EntityManager;

/**
 * Controlador de la clase Mago. Aplica el formato Singleton
 */
public class MagoController {
    private static MagoController instancia;

    private MagoController() {}

    /**
     * Devuelve la instancia del controlador
     * @return la instancia de la clase
     */
    public static MagoController getInstancia() {
        if (instancia == null) {
            instancia = new MagoController();
        }
        return instancia;
    }

    /**
     * Añade la clase a la base de datos
     * @param mago
     */
    public void engadirMago(Mago mago) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mago);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Busca y, si la encuentra, imprime la entidad correspondiente de la base de datos
     * @param mago
     */
    public void buscarMago(Mago mago) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            Mago m = em.find(Mago.class, mago.getId());
            System.out.println(m);
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Borra la entidad correspondiente
     * @param mago
     */
    public void borrarMago(Mago mago) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(mago);
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
     * @param mago
     * @param m
     */
    public void modificarMago(Mago mago, Mago m) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            mago.setHechizos(m.getHechizos());
            mago.setMana(m.getMana());
            mago.setNombre(m.getNombre());
            mago.setVida(m.getVida());

            em.getTransaction().begin();
            em.merge(mago);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
