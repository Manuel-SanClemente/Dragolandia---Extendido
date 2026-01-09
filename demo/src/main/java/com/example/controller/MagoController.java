package com.example.controller;

import com.example.model.HibernateUtil;
import com.example.model.Mago;

import jakarta.persistence.EntityManager;

public class MagoController {
    private static MagoController instancia;

    private MagoController() {
        System.out.println("\nControlador de Mago inicializado\n");
    }

    public static MagoController getInstancia() {
        if (instancia == null) {
            instancia = new MagoController();
        }
        return instancia;
    }

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
