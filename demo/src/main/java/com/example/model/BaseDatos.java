package com.example.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class BaseDatos {
    private static BaseDatos instancia;

    private BaseDatos() {
        System.out.println("\nIniciando conexión con Base de datos \n");
    }

    public static BaseDatos getInstancia() {
        if (instancia == null) {
            instancia = new BaseDatos();            
        }
        return instancia;
    }

    /**
     * OPERACIONES DE CREACÍON
     */

    public void engadirMago(Mago mago){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();

        List<Hechizo> hechizos = mago.getHechizos();
        for (Hechizo hechizo : hechizos) {
            engadirHechizo(hechizo);
        }

        em.getTransaction().begin();
        em.persist(mago);
        em.getTransaction().commit();
        em.close();
    }

    public void engadirHechizo(Hechizo hechizo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(hechizo);
        em.getTransaction().commit();
        em.close();
    }

    public void engadirMonstruo(Monstruo monstruo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(monstruo);
        em.getTransaction().commit();
        em.close();

    }

    public void engadirDragon(Dragon dragon) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(dragon);
        em.getTransaction().commit();
        em.close();

    }

    public void engadirBosque(Bosque bosque) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();

        List<Monstruo> monstruos = bosque.getListaMonstruos();
        Dragon dragon = bosque.getDragon();

        for (Monstruo monstruo : monstruos) {
            engadirMonstruo(monstruo);
        }

        engadirDragon(dragon);
        
        em.getTransaction().begin();
        em.persist(bosque);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * OPERACIONES DE BORRADO
     */

    public void borrarMago(Mago mago){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(mago);
        em.getTransaction().commit();
        em.close();
    }

    public void borrarHechizo(Hechizo hechizo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(hechizo);
        em.getTransaction().commit();
        em.close();
    }

    public void borrarMonstruo(Monstruo monstruo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(monstruo);
        em.getTransaction().commit();
        em.close();

    }

    public void borrarDragon(Dragon dragon) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(dragon);
        em.getTransaction().commit();
        em.close();

    }

    public void borrarBosque(Bosque bosque) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(bosque);
        em.getTransaction().commit();
        em.close();

    }

    /**
     * OPERACIONES DE MODIFICACIÓN
     */

    public void modificarMago(Mago mago){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();

        Mago m = em.find(mago.getClass(), mago.getId());
        m.setHechizos(mago.getHechizos());
        m.setMana(mago.getMana());
        m.setNombre(mago.getNombre());
        m.setVida(mago.getVida());

        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
        em.close();
    }

    public void modificarHechizo(Hechizo hechizo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();

        Hechizo h = em.find(hechizo.getClass(), hechizo.getId());
        h.setEfecto(hechizo.getEfecto());
        h.setMago(hechizo.getMago());
        h.setNombre(hechizo.getNombre());

        em.getTransaction().begin();
        em.merge(h);
        em.getTransaction().commit();
        em.close();
    }

    public void modificarMonstruo(Monstruo monstruo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();

        Monstruo m = em.find(monstruo.getClass(), monstruo.getId());
        m.setBosque(monstruo.getBosque());
        m.setEstado(monstruo.getEstado());
        m.setFuerza(monstruo.getFuerza());
        m.setNombre(monstruo.getNombre());
        m.setRareza(monstruo.getRareza());
        m.setTipo(monstruo.getTipo());
        m.setVida(monstruo.getVida());

        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
        em.close();

    }

    public void modificarDragon(Dragon dragon) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();

        Dragon d = em.find(dragon.getClass(), dragon.getId());
        d.setIntensidadFuego(dragon.getIntensidadFuego());
        d.setNombre(dragon.getNombre());
        d.setResistencia(dragon.getResistencia());

        em.getTransaction().begin();
        em.merge(d);
        em.getTransaction().commit();
        em.close();

    }

    public void modificarBosque(Bosque bosque) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
        EntityManager em = emf.createEntityManager();

        Bosque b = em.find(bosque.getClass(), bosque.getId());
        b.setDragon(bosque.getDragon());
        b.setListaMonstruos(bosque.getListaMonstruos());
        b.setMonstruoJefe(bosque.getMonstruoJefe());
        b.setNivelPeligro(bosque.getNivelPeligro());
        b.setNombre(bosque.getNombre());

        em.getTransaction().begin();
        em.merge(b);
        em.getTransaction().commit();
        em.close();

    }
}
