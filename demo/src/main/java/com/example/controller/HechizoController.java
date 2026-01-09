package com.example.controller;

import com.example.model.Hechizo;
import com.example.model.HibernateUtil;

import jakarta.persistence.EntityManager;

public class HechizoController {
    private static HechizoController instancia;

    private HechizoController() {
        System.out.println("\nIniciando conexión con Base de datos \n");
    }

    public static HechizoController getInstancia() {
        if (instancia == null) {
            instancia = new HechizoController();            
        }
        return instancia;
    }

    public void engadirHechizo(Hechizo hechizo){
        EntityManager em = HibernateUtil.getEntityManager();

        em.getTransaction().begin();
        em.persist(hechizo);
        em.getTransaction().commit();
        em.close();
    }

    public void buscarHechizo(Hechizo hechizo){
        EntityManager em = HibernateUtil.getEntityManager();

        Hechizo h = em.find(Hechizo.class, hechizo.getId());

        System.out.println(h);

        em.close();
    }

    public void borrarHechizo(Hechizo hechizo){
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.remove(hechizo);
        em.getTransaction().commit();
        em.close();
    }

    public void modificarHechizo(Hechizo hechizo, Hechizo h){
        EntityManager em = HibernateUtil.getEntityManager();

        hechizo.setEfecto(h.getEfecto());
        hechizo.setMago(h.getMago());
        hechizo.setNombre(h.getNombre());

        em.getTransaction().begin();
        em.merge(hechizo);
        em.getTransaction().commit();
        em.close();
    }

}
