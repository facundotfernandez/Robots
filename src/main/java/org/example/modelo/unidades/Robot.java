package org.example.modelo.unidades;

import static org.example.modelo.utilidades.Constantes.MULTIPLICADOR_PUNTAJE;

public class Robot extends Personaje {

    private final int multiplicador;

    public Robot(int multiplicador) {
        super(multiplicador * 10);
        this.multiplicador = multiplicador;
    }

    public Robot(int multiplicador, int fila, int columna) {
        super(multiplicador * MULTIPLICADOR_PUNTAJE, fila, columna);
        this.multiplicador = multiplicador;
    }

    public int getMultiplicador() {
        return multiplicador;
    }


}

