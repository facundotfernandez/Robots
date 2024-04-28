package org.robots.modelo.tablero;

import java.util.LinkedList;
import java.util.Random;

public class Tablero<T> {
    private final int filas;
    private final int columnas;
    private final LinkedList<LinkedList<Celda<T>>> grilla;

    /**
     * Inicializa el tablero con fila-columnas dadas
     *
     * @param filas    Dimension en filas
     * @param columnas Dimension en columnas
     */
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.grilla = inicializarGrilla(filas, columnas);
    }

    /**
     * Crea la matriz de tamaño filas x columnas
     *
     * @param filas    Dimension en filas
     * @param columnas Dimension en columnas
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
     *
     * @param fila    Fila dada a consultar
     * @param columna Columna dada a consultar
     * @return celda en la localizacion fila-columna
     * @throws CeldaInvalidaException Si la celda no pertenece al Tablero
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
     * vrifica si una ubicacion fila-columna esta dentro del tablero
     *
     * @param fila    Fila dada a evaluar
     * @param columna Columna dada a evaluar
     * @throws CeldaInvalidaException Si la celda no pertenece al Tablero
     */
    private void estaDentro(int fila, int columna) throws CeldaInvalidaException {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            throw new CeldaInvalidaException("La celda no pertenece a la grilla");
        }
    }

    /**
     * verifica si una celda esta dentro del tablero
     *
     * @param celda Celda dada a evaluar
     * @throws CeldaInvalidaException Si la celda no pertenece al Tablero
     */
    private void estaDentro(Celda<T> celda) throws CeldaInvalidaException {
        if (celda.getFila() < 0 || celda.getFila() >= filas || celda.getColumna() < 0 || celda.getColumna() >= columnas)
            throw new CeldaInvalidaException("La celda no pertenece a la grilla");
    }

    /**
     * @param celda Celda dada a evaluar
     * @return El ocupante de tipo <T> en una celda específica
     * @throws CeldaInvalidaException   Si la celda no pertenece al Tablero
     * @throws CeldaDesocupadaException Si la celda no está ocupada
     */
    private T getOcupante(Celda<T> celda) throws CeldaInvalidaException, CeldaDesocupadaException {
        estaDentro(celda);
        return celda.getOcupante();
    }

    /**
     * @param fila    Fila dada a evaluar
     * @param columna Columna dada a evaluar
     * @return El ocupante de tipo <T> en una celda específica
     * @throws CeldaInvalidaException   Si la celda no pertenece al Tablero
     * @throws CeldaDesocupadaException Si la celda no está ocupada
     */
    public T getOcupante(int fila, int columna) throws CeldaInvalidaException, CeldaDesocupadaException {
        Celda<T> celda = getCelda(fila, columna);
        return getOcupante(celda);
    }

    /**
     * mueve a la entidad en una fila-columna origen en una direccion determinada, si es que es la hay
     *
     * @param filaOrigen    Fila de origen dada a mover en cierta dirección
     * @param columnaOrigen Columna de origen dada a mover en cierta dirección
     * @param direccion     Dirección del movimiento
     * @throws CeldaInvalidaException   Si la celda no pertenece al Tablero
     * @throws CeldaDesocupadaException Si la celda de origen a mover no está ocupada
     * @throws CeldaOcupadaException    Si la celda de destino ya está ocupada
     */
    public void mover(int filaOrigen, int columnaOrigen, int[] direccion) throws CeldaInvalidaException, CeldaDesocupadaException, CeldaOcupadaException {
        Celda<T> origen = getCelda(filaOrigen, columnaOrigen);
        Celda<T> destino = getCelda(filaOrigen + direccion[0], columnaOrigen + direccion[1]);
        destino.ocupar(origen.vaciar());
    }

    /**
     * mueve a la entidad en una fila-columna origen en una fila-columna destino, si es que es la hay
     *
     * @param filaOrigen     Fila de origen dada a mover
     * @param filaDestino    Fila de destino dada
     * @param columnaDestino Columna de destino dada
     * @param columnaOrigen  Columna de origen dada a mover
     * @throws CeldaInvalidaException   Si la celda no pertenece al Tablero
     * @throws CeldaDesocupadaException Si la celda de origen a mover no está ocupada
     * @throws CeldaOcupadaException    Si la celda de destino ya está ocupada
     */
    public void mover(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) throws CeldaInvalidaException, CeldaDesocupadaException, CeldaOcupadaException {
        Celda<T> origen = getCelda(filaOrigen, columnaOrigen);
        Celda<T> destino = getCelda(filaDestino, columnaDestino);
        destino.ocupar(origen.vaciar());
    }

    /**
     * ubica al ocupante en una celda aleatoria
     *
     * @param ocupante Ocupante a ubicar
     * @return Ubicación de la celda obtenida
     * @throws CeldaOcupadaException Si la celda encontrada ya está ocupada
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

    /**
     * ubica en el centro del tablero al ocupante
     *
     * @param ocupante Ocupante a ubicar
     * @throws CeldaOcupadaException Si la celda ya está ocupada
     */
    public void ubicarEnCentro(T ocupante) throws CeldaOcupadaException {
        getCeldaCentral().ocupar(ocupante);
    }

    /**
     * fuerza la ocupacion de una fila-columna
     *
     * @param fila     Fila dada a ocupar
     * @param columna  Columna dada a ocupar
     * @param ocupante Ocupante a ubicar
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
