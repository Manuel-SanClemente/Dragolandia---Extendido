package com.example.controller;

import com.example.HibernateUtil;
import com.example.model.Mago;

import jakarta.persistence.EntityManager;

/**
 * Controlador de la clase Mago. Aplica el formato Singleton
 */
public class MagoController {
    private static MagoController instancia;

    private MagoController() {}

    /**
     * Devuelve la instancia del controlador
     * @return la instancia de la clase
     */
    public static MagoController getInstancia() {
        if (instancia == null) {
            instancia = new MagoController();
        }
        return instancia;
    }

    /**
     * Añade la clase a la base de datos
     * @param mago
     */
    public void engadirMago(Mago mago) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mago);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al añadir mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Busca y, si la encuentra, imprime la entidad correspondiente de la base de datos
     * @param mago
     */
    public void buscarMago(Mago mago) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            Mago m = em.find(Mago.class, mago.getId());
            if (m != null) {
                System.out.println(m);
            } else {
                System.out.println("Mago no encontrado con ID: " + mago.getId());
            }
        } catch (Exception e) {
            System.err.println("Error al buscar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Borra la entidad correspondiente
     * @param mago
     */
    public void borrarMago(Mago mago) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            // Primero obtenemos la entidad gestionada
            Mago m = em.find(Mago.class, mago.getId());
            if (m != null) {
                em.remove(m);
            } else {
                System.out.println("Mago no encontrado para borrar con ID: " + mago.getId());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al borrar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Modifica la entidad con los datos de la otra clase.
     * Se le deberan pasar dos datos a la Base de Datos: la entidad original, más la versión modificada de la misma.
     * Con ambas, se aplican las modificaciones de la modificada a la original, y las unimos en la BD
     * @param mago
     * @param m
     */
    public void modificarMago(Mago mago, Mago m) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            // Obtenemos la entidad gestionada
            Mago magoGestionado = em.find(Mago.class, mago.getId());
            
            if (magoGestionado != null) {
                magoGestionado.setHechizos(m.getHechizos());
                magoGestionado.setMana(m.getMana());
                magoGestionado.setNombre(m.getNombre());
                magoGestionado.setVida(m.getVida());
                
                em.merge(magoGestionado);
            } else {
                System.out.println("Mago no encontrado para modificar con ID: " + mago.getId());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al modificar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}