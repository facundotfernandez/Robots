package org.robots.modelo.tablero;

public class CeldaDesocupadaException extends Exception {

    public CeldaDesocupadaException() {
        super();
    }

    public CeldaDesocupadaException(String message) {
        super(message);
    }

    public CeldaDesocupadaException(String message, int fila, int columna) {
        super(message + " (" + fila + "," + columna + ")");
    }
}
