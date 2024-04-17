package org.example.modelo.unidades;

public class Robot extends Personaje{

    private final int multiplicador;
    private final Jugador jugador;

    public Robot(int multiplicador, Jugador jugador) {
        super(multiplicador * 10);
        this.multiplicador = multiplicador;
        this.jugador = jugador;
    }

    public int getMultiplicador() {
        return multiplicador;
    }
}

