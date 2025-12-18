# DRAGOLANDIA - VERSIÓN 2

#### Autor: Manuel Dono Araújo

## INTRODUCCIÓN

Dragolandia consiste de un juego de rol de combate por turnos, en el que el jugador toma control de un mago y se adentra en un bosque para enfrentarse a diferentes monstruos.

El jugador debera determinar sus estadisticas antes de adentrarse en el bosque, en el cual se encontrara con diferentes tipos de monstrous. El combate sera por turnos, en el que cada parte debera usar su turno para realizar ataques y movimientos.

Un mago tiene acceso a una lista de hechizos, los cuales puede usar para atacar a un monstruo. Al atacar al monstruo con uno de esos hechizos (asignados aleatoriamente), se le pasara un efecto especifico al monstruo.

Existe un dragón que solo puede habitar en un bosque. Este puede exhalar fuego.

## ANALISIS

El programa esta compuesto de las siguientes clases: Bosque, Monstruo, Mago, Hechizo. Cada uno tiene los siguientes atributos:

- Mago: id, nombre, vida, mana, hechizos
- Hechizo: id, nombre, efecto, mago
- Monstruo: id, nombre, vida, fuerza, tipo, rareza, estado
- Bosque: id, nombre, nivelPeligro, monstruoJefe, listaMonstruos
- Dragon: id, nombre, intensidadFuego, resistencia

#### DIAGRAMA DE CLASES

```mermaid
classDiagram
    class Mago{
        <<class>>
        -int ID
        -String nombre
        -String vida
        -String mana
        -List<Hechizo> hechizos
        +lanzarHechizo(Monstruo)
        +lanzarHechizo(Monstruo, Hechizo)
    }
    class Hechizo {
        <<class>>
        -int ID
        -String nombre
        -Efecto efecto
        -Mago mago
        +aplicarEfecto(Monstruo monstruo)
    }
    class Monstruo{
        <<class>>
        -int ID
        -String nombre
        -Integer vida
        -Integer fuerza
        -Tipo tipo
        -Rareza rareza
        -Estado estado
        +atacarMago(Mago mago)
        +compararMonstruo(Monstruo other)
    }
    class Dragon{
        <<class>>
        -Integer ID
        -String nombre
        -Integer intensidadFuego
        -Integer resistencia
        +exhalar(Monstruo)
        +exhalar(Mago)
    }
    class Bosque{
        <<class>>
        -Integer id
        -String nombre
        -Integer nivelPeligro
        -Monstruo monstruoJefe
        -List<Monstruo> listaMonstruos
        +mostrarJefe()
        +cambiarJefe(Monstruo nuevoJefe)
        +engadirMonstruo(Monstruo nuevoMonstruo)
    }
    class Tipo{
        <<enumeration>>
        OGRO
        TROLL
        ESPECTRO
    }
    class Rareza{
        <<enumeration>>
        NORMAL
        RARO
        EPICO
    }
    class Efecto{
        <<enumeration>>
        NIEVE
        FUEGO
        RAYO
        AGUA
        TIERRA
        PSIQUE
    }
    class Estado{
        <<enumeration>>
        NORMAL
        CONGELADO
        QUEMADO
        ELECTROCUTADO
        MOJADO
        ENTERRADO
        CONFUNDIDO
    }

    Monstruo --> Tipo
    Monstruo --> Rareza
    Monstruo --> Estado
    Hechizo --> Efecto
    Mago <--> Hechizo
    Mago --> Monstruo
    Mago <-- Monstruo
    Bosque --> Monstruo
    Dragon --> Monstruo
    Dragon --> Mago


```

## DISEÑO

El juego esta diseñado de la siguiente forma:

- Un bosque alberga una lista de monstruos, y de ellos se establece un monstruo jefe. 

- En ese bosque, un mago entra. El mago lucha con los monstruos del bosque. 

- En un bosque, también habita un dragón. Un dragón podra atacar a un monstruo o mago con su aliento de fuego.

- Un monstruo puede ser de un tipo y rareza especificos, y puede tener uno o varios efectos.

- Un mago tiene una serie de hechizos. Con ellos, puede utilizarlos para aplicar efectos al monstruo que este atacando.

#### DIAGRAMA ENTIDAD-RELACIÓN

```mermaid
erDiagram
    Monstruo }|--|| Bosque : tiene
    Mago ||--|| Monstruo : lanzaHechizo
    Mago ||--|| Monstruo : lanzaHechizoEspecial
    Monstruo ||--|| Mago : ataca
    Hechizo }|--|{ Mago : tieneHechizo
    Dragon ||--|| Mago: exhala
    Dragon ||--|| Monstruo: exhala
    Dragon ||--|| Bosque: pertenece
  

    Mago {
        int ID PK
        String nombre
        String vida
        String mana
        List hechizos FK
    }

    Hechizo {
        Integer id PK
        String nombre
        Efecto efecto
        Mago mago FK
    }

    Monstruo {
        int ID PK
        String nombre
        Integer vida
        Integer fuerza
        Tipo tipo
        Rareza rareza
        Estado estado
    }

    Dragon {
        Integer id PK
        String nombre
        Integer intensidadFuego
        Integer resistencia
    }

    Bosque {
        Integer id PK
        String nombre
        Integer nivelPeligro
        List monstruos FK
        Monstruo monstruoJefe FK
        Dragon dragon FK
    }
```