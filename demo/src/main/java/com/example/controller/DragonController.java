package com.example.controller;

import com.example.model.Dragon;
import com.example.model.HibernateUtil;

import jakarta.persistence.EntityManager;

public class DragonController {
    private static DragonController instancia;

    private DragonController() {
        System.out.println("\nControlador de Dragon inicializado\n");
    }

    public static DragonController getInstancia() {
        if (instancia == null) {
            instancia = new DragonController();
        }
        return instancia;
    }

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
