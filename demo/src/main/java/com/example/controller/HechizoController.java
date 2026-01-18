package com.example.controller;

import com.example.HibernateUtil;
import com.example.model.Hechizo;

import jakarta.persistence.EntityManager;

/**
 * Controlador de la clase Hechizo. Aplica el formato Singleton
 */
public class HechizoController {
    private static HechizoController instancia;

    private HechizoController() {}

    /**
     * Devuelve la instancia del controlador
     * @return la instancia de la clase
     */
    public static HechizoController getInstancia() {
        if (instancia == null) {
            instancia = new HechizoController();
        }
        return instancia;
    }

    /**
     * Añade la clase a la base de datos
     * @param hechizo
     */
    public void engadirHechizo(Hechizo hechizo) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(hechizo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al añadir hechizo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Busca y, si la encuentra, imprime la entidad correspondiente de la base de datos
     * @param hechizo
     */
    public void buscarHechizo(Hechizo hechizo) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            Hechizo h = em.find(Hechizo.class, hechizo.getId());
            if (h != null) {
                System.out.println(h);
            } else {
                System.out.println("Hechizo no encontrado con ID: " + hechizo.getId());
            }
        } catch (Exception e) {
            System.err.println("Error al buscar hechizo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Borra la entidad correspondiente
     * @param hechizo
     */
    public void borrarHechizo(Hechizo hechizo) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            // Primero obtenemos la entidad gestionada
            Hechizo h = em.find(Hechizo.class, hechizo.getId());
            if (h != null) {
                em.remove(h);
            } else {
                System.out.println("Hechizo no encontrado para borrar con ID: " + hechizo.getId());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al borrar hechizo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Modifica la entidad con los datos de la otra clase.
     * Se le deberan pasar dos datos a la Base de Datos: la entidad original, más la versión modificada de la misma.
     * Con ambas, se aplican las modificaciones de la modificada a la original, y las unimos en la BD
     * @param hechizo
     * @param h
     */
    public void modificarHechizo(Hechizo hechizo, Hechizo h) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            // Obtenemos la entidad gestionada
            Hechizo hechizoGestionado = em.find(Hechizo.class, hechizo.getId());
            
            if (hechizoGestionado != null) {
                hechizoGestionado.setEfecto(h.getEfecto());
                hechizoGestionado.setMago(h.getMago());
                hechizoGestionado.setNombre(h.getNombre());
                
                em.merge(hechizoGestionado);
            } else {
                System.out.println("Hechizo no encontrado para modificar con ID: " + hechizo.getId());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al modificar hechizo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}