package com.example.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class baseDatos {
    SessionFactory factory;

    public baseDatos() {
        System.out.println("\nIniciando conexión con Base de datos \n");

        // Se obtiene la configuración de la conexión con hibernate
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        // Se construye la conexion con la Base de Datos
        this.factory = config.buildSessionFactory();
    }

    /**
     * Se añaden los parametros (que representan diferentes entidades) a la Base de Datos por Hibernate
     * @param mago un mago, con todos sus atributos
     * @param bosque un bosque, con todos sus atributos
     * @param monstruo un monstruo, con todos sus atributos
     * @param hechizos una lista de hechizos
     * @param dragon un dragón, con todos sus atributos
     */
    public void engadirEstructura(Mago mago, Bosque bosque, Monstruo monstruo, List<Hechizo> hechizos, Dragon dragon){

        // Se abre la conexión y se hace una transacción con las clases.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Por cada hechizo que se encuentra en la lista de hechizos del mago, se añade a la Base de Datos
        for (Hechizo hechizo : hechizos) {
            session.persist(hechizo);            
        }

        // Se añaden el mago, monstruo, dragón y bosque a la Base de Datos
        session.persist(mago);
        session.persist(monstruo);
        session.persist(dragon);
        session.persist(bosque);

        // Se mandan las clases y se cierra la conexión
        tx.commit();
        session.close();
    }

}
