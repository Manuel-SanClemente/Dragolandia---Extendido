package com.example.model;

import java.util.List;

import jakarta.persistence.EntityManager;


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
        EntityManager em = HibernateUtil.getEntityManager();

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
        EntityManager em = HibernateUtil.getEntityManager();

        em.getTransaction().begin();
        em.persist(hechizo);
        em.getTransaction().commit();
        em.close();
    }

    public void engadirMonstruo(Monstruo monstruo) {
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.persist(monstruo);
        em.getTransaction().commit();
        em.close();

    }

    public void engadirDragon(Dragon dragon) {
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.persist(dragon);
        em.getTransaction().commit();
        em.close();

    }

    public void engadirBosque(Bosque bosque) {
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.persist(bosque);
        em.getTransaction().commit();
        em.close();
    }

    public void engadirBosqueYDemas(Bosque bosque) {
        EntityManager em = HibernateUtil.getEntityManager();

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
     * OPERACIONES DE CONSULTA
     */

    public void buscarMago(Mago mago){
        EntityManager em = HibernateUtil.getEntityManager();

        Mago m = em.find(Mago.class, mago.getId());

        System.out.println(m);

        em.close();
    }

    public void buscarHechizo(Hechizo hechizo){
        EntityManager em = HibernateUtil.getEntityManager();

        Hechizo h = em.find(Hechizo.class, hechizo.getId());

        System.out.println(h);

        em.close();
    }

    public void buscarMonstruo(Monstruo monstruo){
        EntityManager em = HibernateUtil.getEntityManager();

        Monstruo m = em.find(Monstruo.class, monstruo.getId());

        System.out.println(m);

        em.close();
    }

    public void buscarDragon(Dragon dragon){
        EntityManager em = HibernateUtil.getEntityManager();

        Dragon d = em.find(Dragon.class, dragon.getId());

        System.out.println(d);

        em.close();
    }

    public void buscarBosque(Bosque bosque){
        EntityManager em = HibernateUtil.getEntityManager();

        Bosque b = em.find(Bosque.class, bosque.getId());

        System.out.println(b);

        em.close();
    }

    /**
     * OPERACIONES DE BORRADO
     */

    public void borrarMago(Mago mago){
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.remove(mago);
        em.getTransaction().commit();
        em.close();
    }

    public void borrarHechizo(Hechizo hechizo){
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.remove(hechizo);
        em.getTransaction().commit();
        em.close();
    }

    public void borrarMonstruo(Monstruo monstruo) {
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.remove(monstruo);
        em.getTransaction().commit();
        em.close();

    }

    public void borrarDragon(Dragon dragon) {
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.remove(dragon);
        em.getTransaction().commit();
        em.close();

    }

    public void borrarBosque(Bosque bosque) {
        EntityManager em = HibernateUtil.getEntityManager();
        
        em.getTransaction().begin();
        em.remove(bosque);
        em.getTransaction().commit();
        em.close();

    }

    /**
     * OPERACIONES DE MODIFICACIÓN
     */

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

    public void modificarMonstruo(Monstruo monstruo, Monstruo m) {
        EntityManager em = HibernateUtil.getEntityManager();

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
