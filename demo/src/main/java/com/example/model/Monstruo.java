package com.example.model;

import jakarta.persistence.*;

@Entity(name="monstruo")
@Table(name="monstruos")
public class Monstruo {

    // Enumeración de posibles tipos de monstruo. 

    public enum tipo{
        OGRO,
        TROLL,
        ESPECTRO
    }

    // Enumeración de rarezas de monstruo. 

    public enum rareza{
        NORMAL,
        RARO,
        EPICO
    }

    // Enumeración de posibles estados que un monstruo puede tener. 

    public enum Estado{
        NORMAL,
        CONGELADO,
        QUEMADO,
        ELECTROCUTADO,
        MOJADO,
        ENTERRADO,
        CONFUNDIDO
    }

    // ID es generado automaticamente por Identity

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String nombre;
    private Integer vida;
    private Integer fuerza;

    // Valor enumerado de tipo string

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.NORMAL;
    
    // Valor enumerado de tipo string

    @Enumerated(EnumType.STRING)
    private tipo tipo;   

    // Valor enumerado de tipo string

    @Enumerated(EnumType.STRING)
    private rareza rareza;

    // Para establecer la relación con la tabla bosque, se establece una foreign key

    @ManyToOne
    @JoinColumn(name = "bosque_id")
    private Bosque bosque;

    public Monstruo(){}

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

    public Integer getFuerza() {
        return fuerza;
    }

    public void setFuerza(Integer fuerza) {
        this.fuerza = fuerza;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public tipo getTipo() {
        return tipo;
    }

    public void setTipo(tipo tipo) {
        this.tipo = tipo;
    }

    public rareza getRareza() {
        return rareza;
    }

    public void setRareza(rareza rareza) {
        this.rareza = rareza;
    }

    public Bosque getBosque() {
        return bosque;
    }

    public void setBosque(Bosque bosque) {
        this.bosque = bosque;
    }

    @Override
    public String toString() {
        return "Monstruo [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", fuerza=" + fuerza + ", estado="
                + estado + ", tipo=" + tipo + ", rareza=" + rareza + ", bosque=" + bosque + "]";
    }

    /**
     * Recibe como parametro un mago y, basandose en el nivel de mana del mago, reduce los puntos de vida de su contrincante
     * @param mago el mago que esta siendo atacado
     */
    public void atacarMago(Mago mago) {
        Integer vidaMago = mago.getVida();
        Integer fuerza = this.getFuerza();
        Integer nuevaVida = vidaMago-fuerza;
        mago.setVida(nuevaVida);
    }
    
    /**
     * Para compararse con otro otro monstruo. Para ello, utilizamos su rareza.
     * Dependiendo de su rareza, se le proporciona un "peso". Dependiendo de el "peso", un monstruo es elegido.
     * Si su peso es igual, se compara su fuerza
     * @param other el otro monstruo
     * @return el monstruo superior
     */
    public Monstruo compararMonstruo(Monstruo other) {
        rareza m1rareza = this.getRareza();
        rareza m2rareza = other.getRareza();
        Integer pesoMonstruo1 = 0;
        Integer pesoMonstruo2 = 0;

        switch (m1rareza) {
            case NORMAL:
                pesoMonstruo1 = 1;
                break;
            case RARO:
                pesoMonstruo1 = 2;
                break;
            case EPICO:
                pesoMonstruo1 = 3;
                break;
        }

        switch (m2rareza) {
            case NORMAL:
                pesoMonstruo2 = 1;
                break;
            case RARO:
                pesoMonstruo2 = 2;
                break;
            case EPICO:
                pesoMonstruo2 = 3;
                break;
        }

        if (pesoMonstruo1 > pesoMonstruo2) {
            return this;
        } else if (pesoMonstruo1 < pesoMonstruo2) {
            return other;            
        } else if (pesoMonstruo1 == pesoMonstruo2) {
            if (this.getFuerza() > other.getFuerza()) {
                return this;
            } else {
                return other;
            }
        }

        return null;
    }

}
