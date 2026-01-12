package com.example.model;

import java.util.ArrayList;
import java.util.Random;

import com.example.model.Hechizo.Efecto;
import com.example.model.Monstruo.rareza;
import com.example.model.Monstruo.tipo;

public class Juego {
    public Juego(){}

    /**
     * Esta función se encarga de generar un bosque, con un dragón y lista de monstruos asociados.
     * @return el bosque generado
     */
    public Bosque generarEstructuras(){
        ArrayList<Monstruo> listaMonstruos = new ArrayList<Monstruo>();

        for (int i =0; i < 5; i++) {
            Monstruo monstruo = this.nuevoMonstruo();
            listaMonstruos.add(monstruo);
        }

        Dragon dragon = nuevoDragon();
        Bosque bosque = this.nuevoBosque(listaMonstruos, dragon);

        return bosque;
    }

    /**
     * Esta función permite la creación del mago. Además de los atributos del mago en si, se crea una lista de 5 hechizos aleatorios para el mago. 
     * @param nuevoNombre el nombre del mago
     * @param nuevoVida la vida del mago
     * @param nuevoMana la mana del mago
     * @return el mago creado.
     */
    public Mago nuevoMago(String nuevoNombre, Integer nuevoVida, Integer nuevoMana) {
        Mago nuevoMago = new Mago();
        ArrayList<Hechizo> listaHechizos = new ArrayList<Hechizo>();

        for (int i = 0; i < 5; i++) {
            Hechizo hechizoi = generarHechizo(nuevoMago);
            listaHechizos.add(hechizoi);      
        }

        nuevoMago.setNombre(nuevoNombre);
        nuevoMago.setMana(nuevoMana);
        nuevoMago.setVida(nuevoVida);
        nuevoMago.setHechizos(listaHechizos);

        return nuevoMago;
    }

    /**
     * Para definir los atributos del bosque en si. Gracias a esta función, obtenemos el nivel de peligro y el monstruo jefe del bosque, además de establecer el resto de sus atributos.
     * @param listaMonstruos la lista de monstruos del bosque
     * @param dragon el dragón asociado a ese bosque
     * @return el bosque con todos sus atributos definidos.
     */
    public Bosque nuevoBosque(ArrayList<Monstruo> listaMonstruos, Dragon dragon) {
        Bosque nuevoBosque = new Bosque();
        Random random = new Random();

        String nombreBosque = "";
        Integer tipoId = random.nextInt(1, 5);

        switch (tipoId) {
            case 1:
                nombreBosque = "Misterioso";
                break;
            case 2:
                nombreBosque = "Encantado";
                break;
            case 3:
                nombreBosque = "Peligroso";
                break;
            case 4:
                nombreBosque = "Embrujado";
                break;
            case 5:
                nombreBosque = "Fantasmagorico";
                break;
        }

        nuevoBosque.setNombre("Bosque "+nombreBosque);
        nuevoBosque.setListaMonstruos(listaMonstruos);
        nuevoBosque.setDragon(dragon);
        definirJefe(nuevoBosque, listaMonstruos);

        rareza rarezaJefe = nuevoBosque.getMonstruoJefe().getRareza();

        // Para obtener el nivel de peligro del bosque, se

        if (rarezaJefe == rareza.EPICO) {
            nuevoBosque.setNivelPeligro(3);
        } else if (rarezaJefe == rareza.RARO) {
            nuevoBosque.setNivelPeligro(2);
        } else if (rarezaJefe == rareza.NORMAL) {
            nuevoBosque.setNivelPeligro(1);
        }
        return nuevoBosque;
    }

    /**
     * Para crear un monstruo. Se establecen varios de sus atributos aleatoriamente, y se establecen otros basandose en los resultados.
     * @return el monstruo generado 
     */
    public Monstruo nuevoMonstruo(){
        Monstruo nuevoMonstruo = new Monstruo();
        Random random = new Random();

        Integer nuevaFuerza = random.nextInt(1, 100);
        Integer nuevaVida = random.nextInt(1, 1000);
        Integer tipoId = random.nextInt(1, 3);
        tipo nuevoTipo = tipo.OGRO;

        switch (tipoId) {
            case 1:
                nuevoTipo = tipo.OGRO;
                break;
            case 2:
                nuevoTipo = tipo.TROLL;
                break;
            case 3:
                nuevoTipo = tipo.ESPECTRO;
                break;
        }

        if (75 < nuevaFuerza && nuevaFuerza < 100 || 750 < nuevaVida && nuevaVida < 1000) {
            nuevoMonstruo.setFuerza(nuevaFuerza);
            nuevoMonstruo.setVida(nuevaVida);

            rareza nuevaRareza = rareza.EPICO;
            nuevoMonstruo.setRareza(nuevaRareza);

            nuevoMonstruo.setTipo(nuevoTipo);
            
            nuevoMonstruo.setNombre(nuevoTipo+" "+nuevaRareza);

        } else if (25 < nuevaFuerza && nuevaFuerza < 74 || 250 < nuevaVida && nuevaVida < 749) {
            nuevoMonstruo.setFuerza(nuevaFuerza);
            nuevoMonstruo.setVida(nuevaVida);

            rareza nuevaRareza = rareza.RARO;
            nuevoMonstruo.setRareza(nuevaRareza);

            nuevoMonstruo.setTipo(nuevoTipo);
            
            nuevoMonstruo.setNombre(nuevoTipo+" "+nuevaRareza);

        } else if (1 < nuevaFuerza && nuevaFuerza < 24 || 1 < nuevaVida && nuevaVida < 249) {
            nuevoMonstruo.setFuerza(nuevaFuerza);
            nuevoMonstruo.setVida(nuevaVida);

            rareza nuevaRareza = rareza.NORMAL;
            nuevoMonstruo.setRareza(nuevaRareza);

            nuevoMonstruo.setTipo(nuevoTipo);
            
            nuevoMonstruo.setNombre(nuevoTipo+" "+nuevaRareza);

        } else {
            nuevoMonstruo();
        }

        return nuevoMonstruo;
    }

    /**
     * Para crear un dragón. Se establecen sus atributos aleatoriamente.
     * @return el dragón creado.
     */
    public Dragon nuevoDragon(){
        Random random = new Random();

        ArrayList<String> nombres = new ArrayList<String>(); 
        nombres.add("MIO");
        nombres.add("TUY");
        nombres.add("DAG");
        
        Dragon dragon = new Dragon();
        
        dragon.setNombre(nombres.get(random.nextInt(0, nombres.size())));
        dragon.setIntensidadFuego(random.nextInt(250, 750));
        dragon.setResistencia(random.nextInt(500, 10000));

        return dragon;
    }

    /**
     * Para definir el monstruo jefe del bosque. Simplemente, se escoge a uno de la lista aleatoriamente
     * @param bosque
     * @param listaMonstruos
     */
    public void definirJefe(Bosque bosque, ArrayList<Monstruo> listaMonstruos){
        Random random = new Random();

        Integer tamano = listaMonstruos.size();
        Integer index = random.nextInt(0,tamano); 
        bosque.cambiarJefe(listaMonstruos.get(index));
    }

    /**
     * Para generar un hechizo para el mago. Este se relaciona al mago para el que se esta generando el hechizo, y dependiendo del efecto (escogido aleatoriamente), se le da un nombre.
     * @param mago el mago al que el hechizo se le va a relacionar
     * @return el hechizo generado
     */
    public Hechizo generarHechizo(Mago mago) {
        Random random = new Random();
        Hechizo hechizo = new Hechizo();

        Efecto[] efectos = Efecto.values();
        Integer index = random.nextInt(1,efectos.length);
        
        Efecto efecto = efectos[index];
        
        switch (efecto) {
            case NIEVE:
                hechizo.setNombre("Bola de nieve");
                hechizo.setEfecto(efecto);
                break;
            case FUEGO:
                hechizo.setNombre("Bola de fuego");
                hechizo.setEfecto(efecto);
                break;
            case RAYO:
                hechizo.setNombre("Rayo");
                hechizo.setEfecto(efecto);
                break;
            case AGUA:
                hechizo.setNombre("Torbellino de agua");
                hechizo.setEfecto(efecto);
                break;
            case TIERRA:
                hechizo.setNombre("Pueblo de tierra");
                hechizo.setEfecto(efecto);
                break;
            case PSIQUE:
                hechizo.setNombre("Ataque psiquico");
                hechizo.setEfecto(efecto);
                break;
        }

        hechizo.setMago(mago);
        return hechizo;
    }
    
}