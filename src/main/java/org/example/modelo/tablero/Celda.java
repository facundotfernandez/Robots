package org.example.modelo.tablero;

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

    int getX() {
        return coordenadas.x();
    }

    int getY() {
        return coordenadas.y();
    }

    T getOcupante() {
        return ocupante;
    }

    T vaciar() {
        var anteriorOcupante = ocupante;
        this.ocupante = null;
        return anteriorOcupante;
    }

    void ocupar(T ocupante) {
        this.ocupante = ocupante;
    }

    boolean estaVacia() {
        return ocupante == null;
    }
}
