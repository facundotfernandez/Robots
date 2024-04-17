package org.example.modelo.tablero;

import java.util.LinkedList;
import java.util.Random;

public class Tablero<T> {
    private final int filas;
    private final int columnas;
    private final LinkedList<LinkedList<Celda<T>>> grilla;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.grilla = inicializarGrilla(filas, columnas);
    }

    private LinkedList<LinkedList<Celda<T>>> inicializarGrilla(int filas, int columnas) {
        var grilla = new LinkedList<LinkedList<Celda<T>>>();

        for (int i = 0; i < filas; i++) {
            LinkedList<Celda<T>> fila = new LinkedList<>();
            for (int j = 0; j < columnas; j++) fila.add(new Celda<>(i, j));
            grilla.add(fila);
        }
        return grilla;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    private Celda<T> getCelda(int fila, int columna) throws CeldaInvalidaException {
        estaDentro(fila, columna);
        return grilla.get(fila).get(columna);
    }

    private Celda<T> getCeldaCentral() {
        return grilla.get(filas / 2).get(columnas / 2);
    }

    private Celda<T> getCeldaRandom() {
        return grilla.get(new Random().nextInt(filas)).get(new Random().nextInt(columnas));
    }

    private void ocuparCeldaCentral(T ocupante) throws CeldaInvalidaException {
        getCeldaCentral().ocupar(ocupante);
    }

    private void ocuparCeldaRandom(T ocupante) throws CeldaInvalidaException {
        Celda<T> celda;
        do {
            celda = getCeldaRandom();
        } while (!celda.estaVacia());

        celda.ocupar(ocupante);
    }

    private void estaDentro(int fila, int columna) throws CeldaInvalidaException {
        if (fila < 0 || fila > filas || columna < 0 || columna > columnas)
            throw new CeldaInvalidaException("La celda no pertenece a la grilla");
    }

    private void estaDentro(Celda<T> celda) throws CeldaInvalidaException {
        if (celda.getY() < 0 || celda.getY() > filas || celda.getX() < 0 || celda.getX() > columnas)
            throw new CeldaInvalidaException("La celda no pertenece a la grilla");
    }

    public T getOcupante(int fila, int columna) throws CeldaInvalidaException {
        estaDentro(fila, columna);
        return grilla.get(fila).get(columna).getOcupante();
    }

    private boolean estaVacia(int fila, int columna) throws CeldaInvalidaException {
        estaDentro(fila, columna);
        return grilla.get(fila).get(columna).estaVacia();
    }

    public void mover(Celda<T> origen, Celda<T> destino) throws CeldaInvalidaException {
        estaDentro(origen);
        estaDentro(destino);
        destino.ocupar(origen.vaciar());
    } // TODO Ver tema crasheos

    public void ubicar(T ocupante) {
    }

    public void ubicarEnCentro(T ocupante) {
    }

    public void ubicar(T ocupante, int fila, int columna) {
    }
}
