package org.example.modelo.unidades;

public class Robot extends Personaje{

    private final int multiplicador;

    public Robot(int puntaje, int multiplicador) {
        super(puntaje);
        this.multiplicador = multiplicador;
    }

    public int getMultiplicador() {
        return multiplicador;
    }
}

