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

    public int getX() {
        return coordenadas.x();
    }

    public int getY() {
        return coordenadas.y();
    }

    public T getOcupante() throws CeldaInvalidaException {
        if (estaVacia()) throw new CeldaInvalidaException("La celda no está ocupada");
        return ocupante;
    }

    public T vaciar() throws CeldaInvalidaException {
        if (estaVacia()) throw new CeldaInvalidaException("La celda no está ocupada");
        var anteriorOcupante = ocupante;
        this.ocupante = null;
        return anteriorOcupante;
    }

    public void ocupar(T ocupante) throws CeldaInvalidaException {
        if (!estaVacia()) throw new CeldaInvalidaException("La celda ya está ocupada");
        this.ocupante = ocupante;
    }

    boolean estaVacia() {
        return ocupante == null;
    }
}
