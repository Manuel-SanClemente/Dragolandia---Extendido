package com.example.controller;

import com.example.HibernateUtil;
import com.example.model.Dragon;

import jakarta.persistence.EntityManager;

/**
 * Controlador de la clase Dragon. Aplica el formato Singleton 
 */
public class DragonController {
    private static DragonController instancia;

    private DragonController() {}

    /**
     * Devuelve la instancia del controlador
     * @return la instancia de la clase
     */
    public static DragonController getInstancia() {
        if (instancia == null) {
            instancia = new DragonController();
        }
        return instancia;
    }

    /**
     * Añade la clase a la base de datos
     * @param dragon
     */
    public void engadirDragon(Dragon dragon) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(dragon);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al añadir dragon: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Busca y, si la encuentra, imprime la entidad correspondiente de la base de datos
     * @param dragon
     */
    public void buscarDragon(Dragon dragon) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            Dragon d = em.find(Dragon.class, dragon.getId());
            if (d != null) {
                System.out.println(d);
            } else {
                System.out.println("Dragon no encontrado con ID: " + dragon.getId());
            }
        } catch (Exception e) {
            System.err.println("Error al buscar dragon: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Borra la entidad correspondiente
     * @param dragon
     */
    public void borrarDragon(Dragon dragon) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            // Primero obtenemos la entidad gestionada
            Dragon d = em.find(Dragon.class, dragon.getId());
            if (d != null) {
                em.remove(d);
            } else {
                System.out.println("Dragon no encontrado para borrar con ID: " + dragon.getId());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al borrar dragon: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Modifica la entidad con los datos de la otra clase.
     * Se le deberan pasar dos datos a la Base de Datos: la entidad original, más la versión modificada de la misma.
     * Con ambas, se aplican las modificaciones de la modificada a la original, y las unimos en la BD
     * @param dragon
     * @param d
     */
    public void modificarDragon(Dragon dragon, Dragon d) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            // Obtenemos la entidad gestionada
            Dragon dragonGestionado = em.find(Dragon.class, dragon.getId());
            
            if (dragonGestionado != null) {
                dragonGestionado.setIntensidadFuego(d.getIntensidadFuego());
                dragonGestionado.setNombre(d.getNombre());
                dragonGestionado.setResistencia(d.getResistencia());
                
                em.merge(dragonGestionado);
            } else {
                System.out.println("Dragon no encontrado para modificar con ID: " + dragon.getId());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al modificar dragon: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}