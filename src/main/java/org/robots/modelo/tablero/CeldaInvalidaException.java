package org.robots.modelo.tablero;

public class CeldaInvalidaException extends IndexOutOfBoundsException {

    public CeldaInvalidaException() {
        super();
    }

    public CeldaInvalidaException(String message) {
        super(message);
    }

    public CeldaInvalidaException(String message, int fila, int columna) {
        super(message + " (" + fila + "," + columna + ")");
    }
}
