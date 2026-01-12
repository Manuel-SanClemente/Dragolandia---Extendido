package com.example.controller;

import com.example.model.Hechizo;
import com.example.model.HibernateUtil;

import jakarta.persistence.EntityManager;

/**
 * Controlador de la clase Hechizo. Aplica el formato Singleton
 */
public class HechizoController {
    private static HechizoController instancia;

    private HechizoController() {}

    /**
     * Devuelve la instancia del controlador
     * @return la instancia de la clase
     */
    public static HechizoController getInstancia() {
        if (instancia == null) {
            instancia = new HechizoController();
        }
        return instancia;
    }

    /**
     * Añade la clase a la base de datos
     * @param hechizo
     */
    public void engadirHechizo(Hechizo hechizo) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(hechizo);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    /**
     * Busca y, si la encuentra, imprime la entidad correspondiente de la base de datos
     * @param hechizo
     */
    public void buscarHechizo(Hechizo hechizo) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            Hechizo h = em.find(Hechizo.class, hechizo.getId());
            System.out.println(h);
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Borra la entidad correspondiente
     * @param hechizo
     */
    public void borrarHechizo(Hechizo hechizo) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(hechizo);
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
     * @param hechizo
     * @param h
     */
    public void modificarHechizo(Hechizo hechizo, Hechizo h) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            hechizo.setEfecto(h.getEfecto());
            hechizo.setMago(h.getMago());
            hechizo.setNombre(h.getNombre());

            em.getTransaction().begin();
            em.merge(hechizo);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
