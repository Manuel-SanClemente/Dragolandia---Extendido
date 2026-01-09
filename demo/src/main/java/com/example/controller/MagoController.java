package com.example.controller;

import com.example.model.HibernateUtil;
import com.example.model.Mago;

import jakarta.persistence.EntityManager;

public class MagoController {
    private static MagoController instancia;

    private MagoController() {
        System.out.println("\nIniciando conexión con Base de datos \n");
    }

    public static MagoController getInstancia() {
        if (instancia == null) {
            instancia = new MagoController();            
        }
        return instancia;
    }

    public void engadirMago(Mago mago){
        EntityManager em = HibernateUtil.getEntityManager();

        em.getTransaction().begin();
        em.persist(mago);
        em.getTransaction().commit();
        em.close();
    }

    public void buscarMago(Mago mago){
        EntityManager em = HibernateUtil.getEntityManager();

        Mago m = em.find(Mago.class, mago.getId());

        System.out.println(m);

        em.close();
    }

    public void borrarMago(Mago mago){
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.remove(mago);
        em.getTransaction().commit();
        em.close();
    }

    public void modificarMago(Mago mago, Mago m){
        EntityManager em = HibernateUtil.getEntityManager();

        mago.setHechizos(m.getHechizos());
        mago.setMana(m.getMana());
        mago.setNombre(m.getNombre());
        mago.setVida(m.getVida());

        em.getTransaction().begin();
        em.merge(mago);
        em.getTransaction().commit();
        em.close();
    }
}
