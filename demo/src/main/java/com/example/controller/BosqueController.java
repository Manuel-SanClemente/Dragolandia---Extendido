package com.example.controller;

import com.example.HibernateUtil;
import com.example.model.Bosque;

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
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al añadir bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
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
            if (b != null) {
                System.out.println(b);
            } else {
                System.out.println("Bosque no encontrado con ID: " + bosque.getId());
            }
        } catch (Exception e) {
            System.err.println("Error al buscar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
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
            // Primero obtenemos la entidad gestionada
            Bosque b = em.find(Bosque.class, bosque.getId());
            if (b != null) {
                em.remove(b);
            } else {
                System.out.println("Bosque no encontrado para borrar con ID: " + bosque.getId());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al borrar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
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
            em.getTransaction().begin();
            // Obtenemos la entidad gestionada
            Bosque bosqueGestionado = em.find(Bosque.class, bosque.getId());
            
            if (bosqueGestionado != null) {
                bosqueGestionado.setDragon(b.getDragon());
                bosqueGestionado.setListaMonstruos(b.getListaMonstruos());
                bosqueGestionado.setMonstruoJefe(b.getMonstruoJefe());
                bosqueGestionado.setNivelPeligro(b.getNivelPeligro());
                bosqueGestionado.setNombre(b.getNombre());
                
                em.merge(bosqueGestionado);
            } else {
                System.out.println("Bosque no encontrado para modificar con ID: " + bosque.getId());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al modificar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}