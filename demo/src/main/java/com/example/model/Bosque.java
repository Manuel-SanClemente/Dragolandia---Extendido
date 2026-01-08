package com.example.model;

import java.util.List;

import jakarta.persistence.*;

/**
 * Clase Bosque. Sus atributos son los utilizados para la tabla "bosques" en la Base de datos
 */
@Entity(name="bosque")
@Table(name="bosques")
public class Bosque {

    // ID es generado automaticamente por Identity

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String nombre;
    private Integer nivelPeligro;

    // Para establecer la relación con la tabla monstruo, se establece una foreign key

    @ManyToOne
    @JoinColumn(name = "monstruo_jefe_id")
    private Monstruo monstruoJefe;

    // Para establecer la relación con los monstruos contenidos en la lista, se establece su foreign key

    @OneToMany(mappedBy = "bosque", cascade = CascadeType.ALL, fetch = FetchType.LAZY)    
    private List<Monstruo> listaMonstruos; 

    // Para establecer la relación con el dragón del bosque, se establece una foreign key referenciandolo

    @OneToOne
    @JoinColumn(name = "dragon_id")
    private Dragon dragon;

    public Bosque() {}

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

    public Integer getNivelPeligro() {
        return nivelPeligro;
    }

    public void setNivelPeligro(Integer nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    public List<Monstruo> getListaMonstruos() {
        return listaMonstruos;
    }

    public void setListaMonstruos(List<Monstruo> listaMonstruos) {
        this.listaMonstruos = listaMonstruos;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }

    /**
     * Para mostrar la información del monstruo jefe
     */
    public void mostrarJefe(){
        System.out.println(monstruoJefe);
    }
    
    /**
     * Para establecer un nuevo monstruo jefe
     * @param nuevoJefe
     */
    public void cambiarJefe(Monstruo nuevoJefe){
        setMonstruoJefe(nuevoJefe);
    }

    /**
     * Para añadir un monstruo a la lista
     * @param monstruo
     */
    public void engadirMonstruo(Monstruo monstruo){
        listaMonstruos.add(monstruo);
    }

    @Override
    public String toString() {
        return "Bosque [id=" + id + ", nombre=" + nombre + ", nivelPeligro=" + nivelPeligro + ", monstruoJefe="
                + monstruoJefe + ", listaMonstruos=" + listaMonstruos + ", dragon=" + dragon + "]";
    }

    

    
}
