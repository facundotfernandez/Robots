package org.example.modelo.tablero;

import org.example.modelo.utilidades.Punto;

public class Celda<T> {
    private final Punto coordenadas;
    private T ocupante;

    /**
    * Inicializa la Celda con fila-columna dadas
    *@param fila
    *@param columna
    */
    public Celda(int fila, int columna) {
        this.coordenadas = new Punto(fila, columna);
        this.ocupante = null;
    }

    /**
    * Inicializa la celda con fila-columna y oucpante dados
    *@param fila
    *@param columna
    *@param ocupante
    */
    public Celda(int fila, int columna, T ocupante) {
        this.coordenadas = new Punto(fila, columna);
        this.ocupante = ocupante;
    }

    /**
    *@return fila de la celda actual
    */
    public int getFila() {
        return coordenadas.fila();
    }

    /**
    *@return columna de la celda actual
    */
    public int getColumna() {
        return coordenadas.columna();
    }

    /**
    * Devuelve el ocupante que se encuentre en la celda, si es que la hay
    *@return ocupante de la celda actual
    *@throws CeldaDesocupadaException
    */
    public T getOcupante() throws CeldaDesocupadaException {
        if (estaVacia()) throw new CeldaDesocupadaException();
        return ocupante;
    }

    /**
    * vacía la celda devolviendo el ocupante que se encuentre sobre la misma, si es que la hay
    *@return ocupante de la celda actual
    *@throws CeldaDesocupadaException
    */
    public T vaciar() throws CeldaDesocupadaException {
        if (estaVacia())
            throw new CeldaDesocupadaException("ORIGEN VACIO | Fila: " + coordenadas.fila() + ", columna: " + coordenadas.columna());
        var anteriorOcupante = ocupante;
        this.ocupante = null;
        return anteriorOcupante;
    }

    /**
    *ocupa la celda con una entidad
    *@param ocupante
    *@throws CeldaDesocupadaException
    */
    public void ocupar(T ocupante) throws CeldaOcupadaException {
        if (!estaVacia()) throw new CeldaOcupadaException();
        this.ocupante = ocupante;
    }

    /**
    *@return True si la celda esta vacia, False en caso contrario
    */
    boolean estaVacia() {
        return ocupante == null;
    }
}
