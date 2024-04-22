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

    private void ocuparCeldaCentral(T ocupante) throws CeldaInvalidaException, CeldaOcupadaException {
        getCeldaCentral().ocupar(ocupante);
    }

    private void ocuparCeldaRandom(T ocupante) throws CeldaInvalidaException, CeldaOcupadaException {
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
        if (celda.getFila() < 0 || celda.getFila() > filas || celda.getColumna() < 0 || celda.getColumna() > columnas)
            throw new CeldaInvalidaException("La celda no pertenece a la grilla");
    }

    private T getOcupante(Celda<T> celda) throws CeldaInvalidaException, CeldaDesocupadaException {
        estaDentro(celda);
        return celda.getOcupante();
    }

    public T getOcupante(int fila, int columna) throws CeldaInvalidaException, CeldaDesocupadaException {
        Celda<T> celda = getCelda(fila, columna);
        return getOcupante(celda);
    }

    private boolean estaVacia(int fila, int columna) throws CeldaInvalidaException {
        estaDentro(fila, columna);
        return grilla.get(fila).get(columna).estaVacia();
    }

    public void mover(int filaOrigen, int columnaOrigen, int[] direccion) throws CeldaInvalidaException, CeldaDesocupadaException, CeldaOcupadaException {
        Celda<T> origen = getCelda(filaOrigen, columnaOrigen);
        Celda<T> destino = getCelda(filaOrigen + direccion[0], columnaOrigen + direccion[1]);
        destino.ocupar(origen.vaciar());
    }

    public void mover(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) throws CeldaInvalidaException, CeldaDesocupadaException, CeldaOcupadaException {
        Celda<T> origen = getCelda(filaOrigen, columnaOrigen);
        Celda<T> destino = getCelda(filaDestino, columnaDestino);
        destino.ocupar(origen.vaciar());
    }

    public int[] ubicar(T ocupante) throws CeldaOcupadaException {
        while (true) {
            Celda<T> celdaRandom = getCeldaRandom();
            try {
                celdaRandom.getOcupante();
            } catch (CeldaDesocupadaException _) {
                celdaRandom.ocupar(ocupante);
                return new int[]{celdaRandom.getFila(), celdaRandom.getColumna()};
            }
        }
    }

    public void ubicar(T ocupante, int fila, int columna) throws CeldaOcupadaException {
        getCelda(fila, columna).ocupar(ocupante);
    }

    public void ubicarEnCentro(T ocupante) throws CeldaOcupadaException {
        getCeldaCentral().ocupar(ocupante);
    }

    public void forzarOcupacion(int fila, int columna, Object o) {
    }
}
