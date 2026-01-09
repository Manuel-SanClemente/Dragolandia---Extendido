package com.example.model;

import jakarta.persistence.*;

/**
 * Clase Dragón. Sus atributos son los utilizados para la tabla "dragones" en la base de datos
 */
@Entity(name="dragon")
@Table(name="dragones")
public class Dragon {

    // ID es generado automaticamente por Identity

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String nombre;
    private Integer intensidadFuego;
    private Integer resistencia;

    public Dragon() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIntensidadFuego() {
        return intensidadFuego;
    }

    public void setIntensidadFuego(Integer intensidadFuego) {
        this.intensidadFuego = intensidadFuego;
    }

    public Integer getResistencia() {
        return resistencia;
    }

    public void setResistencia(Integer resistencia) {
        this.resistencia = resistencia;
    }

    @Override
    public String toString() {
        return "Dragon= id:" + id + ", nombre:" + nombre + ", intensidadFuego:" + intensidadFuego + ", resistencia:"
                + resistencia;
    }

    /**
     * El dragón exhala fuego, que, usando el valor de intensidadFuego, resta la vida a un monstruo
     * @param monstruo el monstruo que esta siendo quemado/atacado
     */
    public void exhalar(Monstruo monstruo) {
        monstruo.setVida(monstruo.getVida() - intensidadFuego);        
    }

    /**
     * El dragón exhala fuego, que, usando el valor de intensidadFuego, resta la vida a un mago
     * @param mago el mago que esta siendo quemado/atacado
     */
    public void exhalar(Mago mago) {
        mago.setVida(mago.getVida() - intensidadFuego);        
    }
}
