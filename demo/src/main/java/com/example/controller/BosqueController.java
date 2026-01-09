package com.example.controller;

import com.example.model.Bosque;
import com.example.model.HibernateUtil;

import jakarta.persistence.EntityManager;

public class BosqueController {
    private static BosqueController instancia;

    private BosqueController() {
        System.out.println("\nIniciando conexión con Base de datos \n");
    }

    public static BosqueController getInstancia() {
        if (instancia == null) {
            instancia = new BosqueController();            
        }
        return instancia;
    }


    public void engadirBosque(Bosque bosque) {
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.persist(bosque);
        em.getTransaction().commit();
        em.close();
    }

    public void buscarBosque(Bosque bosque){
        EntityManager em = HibernateUtil.getEntityManager();

        Bosque b = em.find(Bosque.class, bosque.getId());

        System.out.println(b);

        em.close();
    }

    public void borrarBosque(Bosque bosque) {
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.remove(bosque);
        em.getTransaction().commit();
        em.close();

    }

    public void modificarBosque(Bosque bosque, Bosque b) {
        EntityManager em = HibernateUtil.getEntityManager();

        bosque.setDragon(b.getDragon());
        bosque.setListaMonstruos(b.getListaMonstruos());
        bosque.setMonstruoJefe(b.getMonstruoJefe());
        bosque.setNivelPeligro(b.getNivelPeligro());
        bosque.setNombre(b.getNombre());

        em.getTransaction().begin();
        em.merge(bosque);
        em.getTransaction().commit();
        em.close();

    }
}
