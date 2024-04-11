package org.example.modelo.tablero;

public class Tablero<T> {

    private final Grilla<T> grilla;

    public Tablero(int filas, int columnas) {
        this.grilla = new Grilla<T>(filas, columnas);
    }

    public Grilla<T> getGrilla() {
        return grilla;
    }

    public T getOcupante(int fila, int columna) {
        return grilla.getOcupante(fila, columna);
    }
}
