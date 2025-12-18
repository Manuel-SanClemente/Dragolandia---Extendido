package com.example.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class baseDatos {

    public baseDatos() {}

    /**
     * Para añadir un mago, bosque y monstruo jefe a la Base de Datos.  
     * @param mago
     * @param bosque
     * @param monstruo
     */
    public void engadirEstructura(Mago mago, Bosque bosque, Monstruo monstruo, List<Hechizo> hechizos, Dragon dragon){
        System.out.println("\nIniciando conexión con Base de datos \n");

        // Se obtiene la configuración de la conexión con hibernate
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        // Se construye la conexion con la Base de Datos
        SessionFactory factory = config.buildSessionFactory();

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        for (Hechizo hechizo : hechizos) {
            session.persist(hechizo);            
        }

        session.persist(mago);
        session.persist(monstruo);
        session.persist(dragon);
        session.persist(bosque);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();
    }

}
