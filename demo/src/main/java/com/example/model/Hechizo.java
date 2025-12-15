package com.example.model;

import com.example.model.Monstruo.Estado;

import jakarta.persistence.*;

@Entity(name="hechizo")
@Table(name="hechizos")
public class Hechizo {

    public enum Efecto{
        NIEVE,
        FUEGO,
        RAYO,
        AGUA,
        TIERRA,
        PSIQUE
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String nombre;

    @Enumerated(EnumType.STRING)
    private Efecto efecto;

    public Hechizo(){}

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

    public Efecto getEfecto() {
        return efecto;
    }

    public void setEfecto(Efecto efecto) {
        this.efecto = efecto;
    }

    @Override
    public String toString() {
        return "Hechizo [id=" + id + ", nombre=" + nombre + ", efecto=" + efecto + "]";
    }

    public void aplicarEfecto(Monstruo monstruo) {
        switch (this.efecto) {
            case NIEVE:
                monstruo.setEstado(Estado.CONGELADO);
                break;
            case FUEGO:
                monstruo.setEstado(Estado.QUEMADO);
                break;
            case RAYO:
                monstruo.setEstado(Estado.ELECTROCUTADO);
                break;
            case AGUA:
                monstruo.setEstado(Estado.MOJADO);
                break;
            case TIERRA:
                monstruo.setEstado(Estado.ENTERRADO);
                break;
            case PSIQUE:
                monstruo.setEstado(Estado.CONFUNDIDO);
                break;
        }
    }
}
