package com.example.controller;

import com.example.model.Hechizo;
import com.example.model.HibernateUtil;

import jakarta.persistence.EntityManager;

public class HechizoController {
    private static HechizoController instancia;

    private HechizoController() {
        System.out.println("\nControlador de Hechizo inicializado\n");
    }

    public static HechizoController getInstancia() {
        if (instancia == null) {
            instancia = new HechizoController();
        }
        return instancia;
    }

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
