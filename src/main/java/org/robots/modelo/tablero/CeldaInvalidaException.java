package org.robots.modelo.tablero;

public class CeldaInvalidaException extends IndexOutOfBoundsException {

    public CeldaInvalidaException(String message) {
        super(message);
    }
}
