package com.example.model;

import java.util.List;

import jakarta.persistence.*;

/**
 * Clase Mago. Sus atributos son los utilizados para la tabla "magos" en la Base de datos
 */
@Entity(name="mago")
@Table(name="magos")
public class Mago {

    // ID es generado automaticamente por Identity
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String nombre;
    private Integer vida;
    private Integer mana;

    @OneToMany(mappedBy = "mago", cascade = CascadeType.ALL, fetch = FetchType.LAZY)   
    private List<Hechizo> hechizos;

    public Mago() {}

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

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public Integer getMana() {
        return mana;
    }

    public void setMana(Integer mana) {
        this.mana = mana;
    }    

    public List<Hechizo> getHechizos() {
        return hechizos;
    }

    public void setHechizos(List<Hechizo> hechizos) {
        this.hechizos = hechizos;
    }

    @Override
    public String toString() {
        return "Mago [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", mana=" + mana + ", hechizos=" + hechizos
                + "]";
    }

    /**
     * Recibe como parametro un monstruo y, basandose en el nivel de mana del mago, reduce los puntos de vida de su contrincante
     * @param monstruo el monstruo que esa siendo atacado
     */
    public void lanzarHechizo(Monstruo monstruo) {
        Integer vidaMonstruo = monstruo.getVida();
        Integer magia = this.getMana();
        Integer vidaMonstruoNueva = vidaMonstruo - magia;
        monstruo.setVida(vidaMonstruoNueva);
    }

    public void lanzarHechizo(Monstruo monstruo, Hechizo hechizo) {
        Integer vidaMonstruo = monstruo.getVida();
        Integer magia = this.getMana();
        Integer vidaMonstruoNueva = vidaMonstruo - magia;
        monstruo.setVida(vidaMonstruoNueva);
        hechizo.aplicarEfecto(monstruo);
    }
    
}
