package org.example.modelo.tablero;

public class Tablero<T> {

    private final Grilla<T> grilla;

    public Tablero(int filas, int columnas) {
        this.grilla = new Grilla<T>(filas, columnas);
    }

    public Grilla<T> getGrilla() {
        return grilla;
    }

    public T getOcupante(int fila, int columna) throws Exception {
        return grilla.getOcupante(fila, columna);
    }

    public Celda<T> getCeldaCentral() {
        return grilla.getCelda();
    }

    public Celda<T> getCelda(int fila, int columna) throws CeldaInvalidaException {
        return grilla.getCelda(fila, columna);
    }

    public Celda<T> getCelda() {
        return grilla.getCeldaRandom();
    }

    public Celda<T> ocuparCeldaRandom(T ocupante) throws CeldaInvalidaException {
        Celda<T> celda = grilla.getCeldaRandom();
        celda.ocupar(ocupante);
        return celda;
    }

    public void ocuparCeldaCentral(T ocupante) throws CeldaInvalidaException {
        getCeldaCentral().ocupar(ocupante);
    }
}
