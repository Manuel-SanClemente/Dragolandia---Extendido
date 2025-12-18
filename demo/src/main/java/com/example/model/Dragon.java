package com.example.model;

import jakarta.persistence.*;

@Entity(name="dragon")
@Table(name="dragones")
public class Dragon {

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

    public void exhalar(Monstruo monstruo) {
        monstruo.setVida(monstruo.getVida() - intensidadFuego);        
    }
}
