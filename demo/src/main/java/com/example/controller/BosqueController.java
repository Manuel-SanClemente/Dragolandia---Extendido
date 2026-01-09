package com.example.controller;

import com.example.model.Bosque;
import com.example.model.HibernateUtil;

import jakarta.persistence.EntityManager;

/**
 * Controlador de la clase Bosque. Aplica el formato Singleton 
 */

public class BosqueController {
    private static BosqueController instancia;

    private BosqueController() {}

    /**
     * Devuelve la instancia del controlador
     * @return la instancia de la clase
     */
    public static BosqueController getInstancia() {
        if (instancia == null) {
            instancia = new BosqueController();
        }
        return instancia;
    }

    /**
     * Añade la clase a la base de datos
     * @param bosque
     */
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

    /**
     * Busca y, si la encuentra, imprime la entidad correspondiente de la base de datos
     * @param bosque
     */
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

    /**
     * Borra la entidad correspondiente
     * @param bosque
     */
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

    /**
     * Modifica la entidad con los datos de la otra clase.
     * Se le deberan pasar dos datos a la Base de Datos: la entidad original, más la versión modificada de la misma.
     * Con ambas, se aplican las modificaciones de la modificada a la original, y las unimos en la BD
     * @param bosque la versión original de la entidad
     * @param b la versión modificada de la entidad
     */
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
