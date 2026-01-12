package com.example.model;

import com.example.model.Monstruo.Estado;

import jakarta.persistence.*;

/**
 * Clase Hechizo. Sus atributos son los utilizados para la tabla "hechizos" en la base de datos
 */
@Entity(name="hechizo")
@Table(name="hechizos")
public class Hechizo {

    // Enumeración de los posibles efectos que un hechizo puede tener.
    public enum Efecto{
        NIEVE,
        FUEGO,
        RAYO,
        AGUA,
        TIERRA,
        PSIQUE
    }

    // ID es generado automaticamente por Identity

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String nombre;
    private Integer coste;

    // Valor enumerado de tipo string

    @Enumerated(EnumType.STRING)
    private Efecto efecto;

    // Para establecer la relación con la tabla mago, se establece una foreign key

    @ManyToOne
    @JoinColumn(name = "mago_id")
    private Mago mago;

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

    public Mago getMago() {
        return mago;
    }

    public void setMago(Mago mago) {
        this.mago = mago;
    }

    public Integer getCoste() {
        return coste;
    }

    public void setCoste(Integer coste) {
        this.coste = coste;
    }

    @Override
    public String toString() {
        return "Hechizo [id=" + id + ", nombre=" + nombre + ", coste=" + coste + ", efecto=" + efecto + ", mago=" + mago
                + "]";
    }

    /**
     * Para aplicar un efecto al monstruo. Esto se usa en conjunción al un ataque por el mago. 
     * Para cumplir esto, se busca el efecto del hechizo en si, y se aplica un equivalente al monstruo
     * @param monstruo el monstruo receptor del hechizo
     */
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
