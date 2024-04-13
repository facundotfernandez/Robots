package org.example.modelo.tablero;

public class CeldaInvalidaException extends Exception {

    public CeldaInvalidaException() {
        super();
    }

    public CeldaInvalidaException(String message) {
        super(message);
    }
}
