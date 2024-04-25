package org.example.modelo.tablero;

import java.util.LinkedList;
import java.util.Random;

public class Tablero<T> {
    private final int filas;
    private final int columnas;
    private final LinkedList<LinkedList<Celda<T>>> grilla;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.grilla = inicializarGrilla(filas, columnas);
    }

    /**
     *creea la matriz de tamaño filas x columnas
     * @param filas
     * @param columnas
     * @return Matriz de listas enlazadas de listas enlazadas de Celdas<T>
     */
    private LinkedList<LinkedList<Celda<T>>> inicializarGrilla(int filas, int columnas) {
        var grilla = new LinkedList<LinkedList<Celda<T>>>();

        for (int i = 0; i < filas; i++) {
            LinkedList<Celda<T>> fila = new LinkedList<>();
            for (int j = 0; j < columnas; j++) fila.add(new Celda<>(i, j));
            grilla.add(fila);
        }
        return grilla;
    }

    /**
     * @return filas del Tablero
     */
    public int getFilas() {
        return filas;
    }

    /**
     * @return columnas del Tablero
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * Devuelve la celda en fila-columna verificando si está dentro de los limites de la Grilla
     * @param fila
     * @param columna
     * @return celda en la localizacion fila-columna
     * @throws CeldaInvalidaException
     */
    private Celda<T> getCelda(int fila, int columna) throws CeldaInvalidaException {
        estaDentro(fila, columna);
        return grilla.get(fila).get(columna);
    }

    /**
     * @return celda central
     */
    private Celda<T> getCeldaCentral() {
        return grilla.get(filas / 2).get(columnas / 2);
    }

    /**
     * @return celda random
     */
    private Celda<T> getCeldaRandom() {
        return grilla.get(new Random().nextInt(filas)).get(new Random().nextInt(columnas));
    }

    /**
     * ocupa la celda central
     *
     * @param ocupante
     * @throws CeldaInvalidaException
     * @throws CeldaOcupadaException
     */
    private void ocuparCeldaCentral(T ocupante) throws CeldaInvalidaException, CeldaOcupadaException {
        getCeldaCentral().ocupar(ocupante);
    }

    /**
     * ocupa una celda aleaoria
     *
     * @param ocupante
     * @throws CeldaInvalidaException
     * @throws CeldaOcupadaException
     */
    private void ocuparCeldaRandom(T ocupante) throws CeldaInvalidaException, CeldaOcupadaException {
        Celda<T> celda;
        do {
            celda = getCeldaRandom();
        } while (!celda.estaVacia());
        celda.ocupar(ocupante);
    }

    /**
     * vrifica si una ubicacion fila-columna esta dentro del tablero
     *
     * @param fila
     * @param columna
     * @throws CeldaInvalidaException
     */
    private void estaDentro(int fila, int columna) throws CeldaInvalidaException {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            throw new CeldaInvalidaException("La celda no pertenece a la grilla");
        }
    }

    /**
     * vrifica si una celda esta dentro del tablero
     *
     * @param celda
     * @throws CeldaInvalidaException
     */
    private void estaDentro(Celda<T> celda) throws CeldaInvalidaException {
        if (celda.getFila() < 0 || celda.getFila() >= filas || celda.getColumna() < 0 || celda.getColumna() >= columnas)
            throw new CeldaInvalidaException("La celda no pertenece a la grilla");
    }

    /**
     * @param celda
     * @return lo que se encunetre<T> en una celda específica
     * @throws CeldaInvalidaException
     * @throws CeldaDesocupadaException
     */
    private T getOcupante(Celda<T> celda) throws CeldaInvalidaException, CeldaDesocupadaException {
        estaDentro(celda);
        return celda.getOcupante();
    }

    /**
     * @param fila
     * @param columna
     * @return lo que se encuentre en una fila-columna específica
     * @throws CeldaInvalidaException
     * @throws CeldaDesocupadaException
     */
    public T getOcupante(int fila, int columna) throws CeldaInvalidaException, CeldaDesocupadaException {
        Celda<T> celda = getCelda(fila, columna);
        return getOcupante(celda);
    }

    /**
     * @param fila
     * @param columna
     * @return si la ubicacion en fila-columna está vacia
     * @throws CeldaInvalidaException
     */
    private boolean estaVacia(int fila, int columna) throws CeldaInvalidaException {
        estaDentro(fila, columna);
        return grilla.get(fila).get(columna).estaVacia();
    }

    /**
     * mueve a la entidad en una fila-columna origen en una direccion determinada, si es que es la hay
     *
     * @param filaOrigen
     * @param columnaOrigen
     * @param direccion
     * @throws CeldaInvalidaException
     * @throws CeldaDesocupadaException
     * @throws CeldaOcupadaException
     */
    public void mover(int filaOrigen, int columnaOrigen, int[] direccion) throws CeldaInvalidaException, CeldaDesocupadaException, CeldaOcupadaException {
        Celda<T> origen = getCelda(filaOrigen, columnaOrigen);
        Celda<T> destino = getCelda(filaOrigen + direccion[0], columnaOrigen + direccion[1]);
        destino.ocupar(origen.vaciar());
    }

    /**
     * mueve a la entidad en una fila-columna origen en una fila-columna destino, si es que es la hay
     *
     * @param filaOrigen
     * @param columnaOrigen
     * @param filaDestino
     * @param columnaDestino
     * @throws CeldaInvalidaException
     * @throws CeldaDesocupadaException
     * @throws CeldaOcupadaException
     */
    public void mover(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) throws CeldaInvalidaException, CeldaDesocupadaException, CeldaOcupadaException {
        Celda<T> origen = getCelda(filaOrigen, columnaOrigen);
        Celda<T> destino = getCelda(filaDestino, columnaDestino);
        destino.ocupar(origen.vaciar());
    }

    /**
     * @param ocupante
     * @return
     * @throws CeldaOcupadaException
     */
    public int[] ubicar(T ocupante) throws CeldaOcupadaException {
        while (true) {
            Celda<T> celdaRandom = getCeldaRandom();
            try {
                celdaRandom.getOcupante();
            } catch (CeldaDesocupadaException _) {
                celdaRandom.ocupar(ocupante);
                return new int[]{celdaRandom.getFila(), celdaRandom.getColumna()};
            }
        }
    }

    public void ubicar(T ocupante, int fila, int columna) throws CeldaOcupadaException {
        getCelda(fila, columna).ocupar(ocupante);
    }

    /**
     * ubica en el centro del tablero al ocupante
     *
     * @param ocupante
     * @throws CeldaOcupadaException
     */
    public void ubicarEnCentro(T ocupante) throws CeldaOcupadaException {
        getCeldaCentral().ocupar(ocupante);
    }

    /**
     * fuerza la ocupacion de una fila-columna
     *
     * @param fila
     * @param columna
     * @param ocupante
     */
    public void forzarOcupacion(int fila, int columna, T ocupante) throws CeldaOcupadaException {
        Celda<T> celda = getCelda(fila, columna);
        try {
            celda.vaciar();
        } catch (CeldaDesocupadaException _) {
        }
        celda.ocupar(ocupante);
    }
}
