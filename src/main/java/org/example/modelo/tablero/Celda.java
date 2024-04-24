package org.example.modelo.tablero;

import org.example.modelo.utilidades.Punto;

public class Celda<T> {
    private final Punto coordenadas;
    private T ocupante;

    public Celda(int fila, int columna) {
        this.coordenadas = new Punto(fila, columna);
        this.ocupante = null;
    }

    public Celda(int fila, int columna, T ocupante) {
        this.coordenadas = new Punto(fila, columna);
        this.ocupante = ocupante;
    }

    public int getFila() {
        return coordenadas.fila();
    }

    public int getColumna() {
        return coordenadas.columna();
    }

    public T getOcupante() throws CeldaDesocupadaException {
        if (estaVacia()) throw new CeldaDesocupadaException();
        return ocupante;
    }

    public T vaciar() throws CeldaDesocupadaException {
        if (estaVacia())
            throw new CeldaDesocupadaException("ORIGEN VACIO | Fila: " + coordenadas.fila() + ", columna: " + coordenadas.columna());
        var anteriorOcupante = ocupante;
        this.ocupante = null;
        return anteriorOcupante;
    }

    public void ocupar(T ocupante) throws CeldaOcupadaException {
        if (!estaVacia()) throw new CeldaOcupadaException();
        this.ocupante = ocupante;
    }

    boolean estaVacia() {
        return ocupante == null;
    }
}
