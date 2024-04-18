package org.example.modelo.jugabilidad;

public class CeldaOcupadaException extends Exception {

    public CeldaOcupadaException() {
        super();
    }

    public CeldaOcupadaException(String message) {
        super(message);
    }

    public CeldaOcupadaException(String message, int fila, int columna) {
        super(message + " (" + fila + "," + columna + ")");
    }
}
