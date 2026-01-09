package com.example.controller;

import com.example.model.Dragon;
import com.example.model.HibernateUtil;

import jakarta.persistence.EntityManager;

public class DragonController {
    private static DragonController instancia;

    private DragonController() {
        System.out.println("\nIniciando conexión con Base de datos \n");
    }

    public static DragonController getInstancia() {
        if (instancia == null) {
            instancia = new DragonController();            
        }
        return instancia;
    }

    public void engadirDragon(Dragon dragon) {
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.persist(dragon);
        em.getTransaction().commit();
        em.close();

    }

    public void buscarDragon(Dragon dragon){
        EntityManager em = HibernateUtil.getEntityManager();

        Dragon d = em.find(Dragon.class, dragon.getId());

        System.out.println(d);

        em.close();
    }

    public void borrarDragon(Dragon dragon) {
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.remove(dragon);
        em.getTransaction().commit();
        em.close();

    }

    public void modificarDragon(Dragon dragon, Dragon d) {
        EntityManager em = HibernateUtil.getEntityManager();

        dragon.setIntensidadFuego(d.getIntensidadFuego());
        dragon.setNombre(d.getNombre());
        dragon.setResistencia(d.getResistencia());

        em.getTransaction().begin();
        em.merge(dragon);
        em.getTransaction().commit();
        em.close();

    }
}
