package com.example.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Clase encargada de la conexión a la base de datos. 
 * Aplica el patrón Singleton, que hace que solo se acceda a una instancia de la misma.
 * Como atributos, tiene lo siguiente:
 * instancia: refleja la instancia de la BD a la que se va a acceder
 * factory: metodo de hibernate para crear la clase encargada de ejecutar las sentencias SQL pertinentes. 
 */

public class BaseDatos {
    private static BaseDatos instancia;
    private SessionFactory factory;

    /**
     * Constructor de la clase. Crea la SessionFactory accediendo a la configuración de hibernate.
     * Es privado, por lo que no se podra acceder directamente a la clase.
     */
    private BaseDatos() {
        System.out.println("\nIniciando conexión con Base de datos \n");

        // Se obtiene la configuración de la conexión con hibernate
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        // Se construye la conexion con la Base de Datos
        this.factory = config.buildSessionFactory();
    }

    /**
     * Devuelve una instancia de la Base de Datos para poder acceder a ella y realizar las consultas SQL que necesitemos
     * @return la instancia de la Base de Datos
     */
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

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Se obtiene la lista de hechizos del mago, se añaden a la Base de Datos
        List<Hechizo> hechizos = mago.getHechizos();
        for (Hechizo hechizo : hechizos) {
            engadirHechizo(hechizo);
        }

        // Se añaden el mago, monstruo
        session.persist(mago);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();
    }

    public void engadirHechizo(Hechizo hechizo){

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Se añaden el mago, monstruo
        session.persist(hechizo);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();
    }

    public void engadirMonstruo(Monstruo monstruo) {

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Se añaden el mago, monstruo
        session.persist(monstruo);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();

    }

    public void engadirDragon(Dragon dragon) {

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Se añaden el mago, monstruo
        session.persist(dragon);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();

    }

    public void engadirBosque(Bosque bosque) {

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        List<Monstruo> monstruos = bosque.getListaMonstruos();
        Dragon dragon = bosque.getDragon();

        for (Monstruo monstruo : monstruos) {
            engadirMonstruo(monstruo);
        }

        engadirDragon(dragon);

        session.persist(bosque);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();

    }


    /**
     * OPERACIONES DE BORRADO
     */


    public void borrarMago(Mago mago){

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(mago);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();
    }

    public void borrarHechizo(Hechizo hechizo){

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(hechizo);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();
    }

    public void borrarMonstruo(Monstruo monstruo) {

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(monstruo);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();

    }

    public void borrarDragon(Dragon dragon) {

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(dragon);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();

    }

    public void borrarBosque(Bosque bosque) {

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(bosque);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();

    }


    /**
     * OPERACIONES DE MODIFICACIÓN
     */


    public void modificarMago(Mago mago){

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(mago);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();
    }

    public void modificarHechizo(Hechizo hechizo){

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(hechizo);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();
    }

    public void modificarMonstruo(Monstruo monstruo) {

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(monstruo);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();

    }

    public void modificarDragon(Dragon dragon) {

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(dragon);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();

    }

    public void modificarBosque(Bosque bosque) {

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(bosque);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();

    }
}
