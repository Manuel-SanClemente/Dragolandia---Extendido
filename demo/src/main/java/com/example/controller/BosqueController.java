package com.example.controller;

import com.example.model.Bosque;
import com.example.model.HibernateUtil;

import jakarta.persistence.EntityManager;

public class BosqueController {
    private static BosqueController instancia;

    private BosqueController() {
        System.out.println("\nControlador de Bosque inicializado\n");
    }

    public static BosqueController getInstancia() {
        if (instancia == null) {
            instancia = new BosqueController();
        }
        return instancia;
    }

    public void engadirBosque(Bosque bosque) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(bosque);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void buscarBosque(Bosque bosque) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            Bosque b = em.find(Bosque.class, bosque.getId());
            System.out.println(b);
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void borrarBosque(Bosque bosque) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(bosque);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void modificarBosque(Bosque bosque, Bosque b) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            bosque.setDragon(b.getDragon());
            bosque.setListaMonstruos(b.getListaMonstruos());
            bosque.setMonstruoJefe(b.getMonstruoJefe());
            bosque.setNivelPeligro(b.getNivelPeligro());
            bosque.setNombre(b.getNombre());

            em.getTransaction().begin();
            em.merge(bosque);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
