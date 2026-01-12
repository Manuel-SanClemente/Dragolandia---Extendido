package com.example.controller;

import com.example.HibernateUtil;
import com.example.model.Monstruo;

import jakarta.persistence.EntityManager;

/**
 * Controlador de la clase Monstruo. Aplica el formato Singleton
 */
public class MonstruoController {
    private static MonstruoController instancia;

    private MonstruoController() {}

    /**
     * Devuelve la instancia del controlador
     * @return la instancia de la clase
     */
    public static MonstruoController getInstancia() {
        if (instancia == null) {
            instancia = new MonstruoController();
        }
        return instancia;
    }

    /**
     * Añade la clase a la base de datos
     * @param monstruo
     */
    public void engadirMonstruo(Monstruo monstruo) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(monstruo);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Busca y, si la encuentra, imprime la entidad correspondiente de la base de datos
     * @param monstruo
     */
    public void buscarMonstruo(Monstruo monstruo) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            Monstruo m = em.find(Monstruo.class, monstruo.getId());
            System.out.println(m);
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Borra la entidad correspondiente
     * @param monstruo
     */
    public void borrarMonstruo(Monstruo monstruo) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(monstruo);
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
     * @param monstruo
     * @param m
     */
    public void modificarMonstruo(Monstruo monstruo, Monstruo m) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            monstruo.setBosque(m.getBosque());
            monstruo.setEstado(m.getEstado());
            monstruo.setFuerza(m.getFuerza());
            monstruo.setNombre(m.getNombre());
            monstruo.setRareza(m.getRareza());
            monstruo.setTipo(m.getTipo());
            monstruo.setVida(m.getVida());

            em.getTransaction().begin();
            em.merge(monstruo);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

}
