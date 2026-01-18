package com.example.controller;

import com.example.HibernateUtil;
import com.example.model.Monstruo;

import jakarta.persistence.EntityManager;

/**
 * Controlador de la clase Monstruo. Aplica el formato Singleton
 */
public class MonstruoController {
    private static MonstruoController instancia;

    private MonstruoController() {}

    /**
     * Devuelve la instancia del controlador
     * @return la instancia de la clase
     */
    public static MonstruoController getInstancia() {
        if (instancia == null) {
            instancia = new MonstruoController();
        }
        return instancia;
    }

    /**
     * Añade la clase a la base de datos
     * @param monstruo
     */
    public void engadirMonstruo(Monstruo monstruo) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(monstruo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al añadir monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Busca y, si la encuentra, imprime la entidad correspondiente de la base de datos
     * @param monstruo
     */
    public void buscarMonstruo(Monstruo monstruo) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            Monstruo m = em.find(Monstruo.class, monstruo.getId());
            if (m != null) {
                System.out.println(m);
            } else {
                System.out.println("Monstruo no encontrado con ID: " + monstruo.getId());
            }
        } catch (Exception e) {
            System.err.println("Error al buscar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Borra la entidad correspondiente
     * @param monstruo
     */
    public void borrarMonstruo(Monstruo monstruo) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            // Primero obtenemos la entidad gestionada
            Monstruo m = em.find(Monstruo.class, monstruo.getId());
            if (m != null) {
                em.remove(m);
            } else {
                System.out.println("Monstruo no encontrado para borrar con ID: " + monstruo.getId());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al borrar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Modifica la entidad con los datos de la otra clase.
     * Se le deberan pasar dos datos a la Base de Datos: la entidad original, más la versión modificada de la misma.
     * Con ambas, se aplican las modificaciones de la modificada a la original, y las unimos en la BD
     * @param monstruo
     * @param m
     */
    public void modificarMonstruo(Monstruo monstruo, Monstruo m) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            // Obtenemos la entidad gestionada
            Monstruo monstruoGestionado = em.find(Monstruo.class, monstruo.getId());
            
            if (monstruoGestionado != null) {
                monstruoGestionado.setBosque(m.getBosque());
                monstruoGestionado.setEstado(m.getEstado());
                monstruoGestionado.setFuerza(m.getFuerza());
                monstruoGestionado.setNombre(m.getNombre());
                monstruoGestionado.setRareza(m.getRareza());
                monstruoGestionado.setTipo(m.getTipo());
                monstruoGestionado.setVida(m.getVida());
                
                em.merge(monstruoGestionado);
            } else {
                System.out.println("Monstruo no encontrado para modificar con ID: " + monstruo.getId());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al modificar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}