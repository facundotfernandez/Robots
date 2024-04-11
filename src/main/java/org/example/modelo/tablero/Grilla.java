package org.example.modelo.tablero;

import java.util.LinkedList;

public class Grilla<T> {

    private final int filas;
    private final int columnas;

    private final LinkedList<LinkedList<Celda<T>>> grilla;

    public Grilla(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.grilla = inicializarGrilla(filas, columnas);
    }

    private LinkedList<LinkedList<Celda<T>>> inicializarGrilla(int filas, int columnas) {
        var grillaVacia = new LinkedList<LinkedList<Celda<T>>>();

        for (int i = 0; i < filas; i++) {
            LinkedList<Celda<T>> fila = new LinkedList<>();
            for (int j = 0; j < columnas; j++) {
                fila.add(new Celda<T>(i, j));
            }
            grillaVacia.add(fila);
        }
        return grillaVacia;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public LinkedList<LinkedList<Celda<T>>> getGrilla() {
        return grilla;
    }

    public T getOcupante(int fila, int columna) {
        if (!estaDentro(fila, columna)) throw new IndexOutOfBoundsException("La celda no pertenece a la grilla");
        return grilla.get(fila).get(columna).getOcupante();
    }

    private boolean estaDentro(int fila, int columna) {
        return fila < filas && columna < columnas;
    }

    private boolean estaVacia(int fila, int columna) {
        return grilla.get(fila).get(columna).estaVacia();
    }
}
