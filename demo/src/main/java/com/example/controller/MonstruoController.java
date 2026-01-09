package com.example.controller;

import com.example.model.HibernateUtil;
import com.example.model.Monstruo;

import jakarta.persistence.EntityManager;

public class MonstruoController {
    private static MonstruoController instancia;

    private MonstruoController() {
        System.out.println("\nControlador de Monstruo inicializado\n");
    }

    public static MonstruoController getInstancia() {
        if (instancia == null) {
            instancia = new MonstruoController();
        }
        return instancia;
    }

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
