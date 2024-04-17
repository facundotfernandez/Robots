package org.example.modelo.unidades;

public class Robot extends Personaje {

    private final int multiplicador;

    public Robot(int multiplicador) {
        super(multiplicador * 10);
        this.multiplicador = multiplicador;
    }

    public Robot(int multiplicador, int fila, int columna) {
        super(multiplicador * 10, fila, columna);
        this.multiplicador = multiplicador;
    }

    public int getMultiplicador() {
        return multiplicador;
    }


}

