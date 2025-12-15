package com.example.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class Dragon {
    private Integer id;
    private String nombre;
    private Integer intensidadFuego;
    private Integer resistencia;

    @OneToOne
    @JoinColumn(name = "bosque_id")
    private Bosque bosque;

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

    public Bosque getBosque() {
        return bosque;
    }

    public void setBosque(Bosque bosque) {
        this.bosque = bosque;
    }

    public void exhalar(Monstruo monstruo) {
        monstruo.setVida(monstruo.getVida() - intensidadFuego);        
    }
}
