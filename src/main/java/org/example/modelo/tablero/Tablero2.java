package org.example.modelo.tablero;

import org.example.modelo.unidades.Robot;

import java.util.LinkedList;
import java.util.Random;

public class Tablero2<T> {
    private final int filas;
    private final int columnas;
    private final LinkedList<LinkedList<Celda<T>>> grilla;

    public Tablero2(int filas, int columnas) {
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

    public Celda<T> getCelda(int fila, int columna) throws CeldaInvalidaException {
        validarUbicacion(fila, columna);
        return grilla.get(fila).get(columna);
    }

    public Celda<T> getCeldaCentral() {
        return grilla.get(filas / 2).get(columnas / 2);
    }

    public Celda<T> getCeldaRandom() {
        return grilla.get(new Random().nextInt(filas)).get(new Random().nextInt(columnas));
    }

    public void ocuparCeldaCentral(T ocupante) throws CeldaInvalidaException {
        getCeldaCentral().ocupar(ocupante);
    }

    public Celda<T> ocuparCeldaRandom(T ocupante) throws CeldaInvalidaException {
        Celda<T> celda = getCeldaRandom();

        if (celda.estaVacia()) {
            celda.ocupar(ocupante);
        }

        return celda;
    }

    private void validarUbicacion(int fila, int columna) throws CeldaInvalidaException {
        if (!estaDentro(fila, columna)) throw new CeldaInvalidaException("La celda no pertenece a la grilla");
    }

    private boolean estaDentro(int fila, int columna) {
        return fila < filas && columna < columnas;
    }

    public boolean moverPersonaje(Celda<T> origen, Celda<T> destino) throws CeldaInvalidaException {
        try {
            T unidad = origen.vaciar();
            destino.ocupar(unidad);
            return true;
        } catch (CeldaInvalidaException e){
            return false;
        }
    }
    public void explotarRobot(int fila, int columna) throws CeldaInvalidaException {
        grilla.get(fila).get(columna).ocupar((new Robot(0,null)));

    }
}
}
