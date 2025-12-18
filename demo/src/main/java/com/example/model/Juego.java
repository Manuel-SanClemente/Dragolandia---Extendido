package com.example.model;

import java.util.ArrayList;
import java.util.Random;

import com.example.model.Hechizo.Efecto;
import com.example.model.Monstruo.rareza;
import com.example.model.Monstruo.tipo;

public class Juego {
    public Juego(){}

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

    public Bosque nuevoBosque(ArrayList<Monstruo> listaMonstruos, Dragon dragon) {
        Bosque nuevoBosque = new Bosque();
        nuevoBosque.setNombre("Bosque #"+nuevoBosque.getId());
        nuevoBosque.setListaMonstruos(listaMonstruos);
        nuevoBosque.setDragon(dragon);
        definirJefe(nuevoBosque, listaMonstruos);

        rareza rarezaJefe = nuevoBosque.getMonstruoJefe().getRareza();


        if (rarezaJefe == rareza.EPICO) {
            nuevoBosque.setNivelPeligro(3);
            return nuevoBosque;
        } else if (rarezaJefe == rareza.RARO) {
            nuevoBosque.setNivelPeligro(2);
            return nuevoBosque;
        } else if (rarezaJefe == rareza.NORMAL) {
            nuevoBosque.setNivelPeligro(1);
            return nuevoBosque;
        }
        return null;
    }

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

            return nuevoMonstruo;
        } else if (25 < nuevaFuerza && nuevaFuerza < 74 || 250 < nuevaVida && nuevaVida < 749) {
            nuevoMonstruo.setFuerza(nuevaFuerza);
            nuevoMonstruo.setVida(nuevaVida);

            rareza nuevaRareza = rareza.RARO;
            nuevoMonstruo.setRareza(nuevaRareza);

            nuevoMonstruo.setTipo(nuevoTipo);
            
            nuevoMonstruo.setNombre(nuevoTipo+" "+nuevaRareza);

            return nuevoMonstruo;
        } else if (1 < nuevaFuerza && nuevaFuerza < 24 || 1 < nuevaVida && nuevaVida < 249) {
            nuevoMonstruo.setFuerza(nuevaFuerza);
            nuevoMonstruo.setVida(nuevaVida);

            rareza nuevaRareza = rareza.NORMAL;
            nuevoMonstruo.setRareza(nuevaRareza);

            nuevoMonstruo.setTipo(nuevoTipo);
            
            nuevoMonstruo.setNombre(nuevoTipo+" "+nuevaRareza);

            return nuevoMonstruo;
        } else {
            nuevoMonstruo();
        }
        return null;
    }

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

    public void definirJefe(Bosque bosque, ArrayList<Monstruo> listaMonstruos){
        Random random = new Random();

        Integer tamano = listaMonstruos.size();
        Integer index = random.nextInt(0,tamano); 
        bosque.cambiarJefe(listaMonstruos.get(index));
    }

    public Hechizo generarHechizo(Mago mago) {
        Random random = new Random();
        Hechizo hechizo = new Hechizo();

        hechizo.setMago(mago);
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

        return hechizo;
    }
    
}