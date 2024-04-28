package org.example.modelo.jugabilidad;

import org.example.modelo.unidades.Jugador;

import java.util.Random;

import static org.example.modelo.utilidades.Constantes.DIFICIL;
import static org.example.modelo.utilidades.Constantes.FACIL;

public class Juego {
    private final Jugador jugador;
    private final int dificultad;
    private Nivel nivel;
    private boolean enJuego;

    /**
     * Inicializa el Juego con dificultad, fila- columnas dadas
     *
     * @param dificultad Dificultad del juego
     * @param filas      Dimension en filas, del tablero
     * @param columnas   Dimension en columnas, del tablero
     */
    public Juego(int dificultad, int filas, int columnas) throws Exception {
        this.jugador = new Jugador((int) Math.ceil((((Math.pow(dificultad, 2) * 10) / Math.exp(dificultad)))), filas / 2, columnas / 2);
        validarDificultad(dificultad);
        this.dificultad = dificultad;
        this.nivel = new Nivel(1, jugador, dificultad, filas, columnas);
        enJuego = true;
        nivel.iniciar();
    }

    /**
     * verifica si la dificultad del Juego
     *
     * @param dificultad Dificultad del juego
     * @throws Exception Si la dificultad no se encuentra en el rango establecido
     */
    private void validarDificultad(int dificultad) throws Exception {
        if (dificultad < FACIL || dificultad > DIFICIL) {
            throw new Exception("La dificultad no es válida");
        }
    }

    /**
     * Avanza de Nivel y lo inicializa
     *
     * @throws Exception Si la dificultad no se encuentra en el rango establecido
     */
    private void avanzarNivel() throws Exception {
        this.nivel = new Nivel(nivel.getId() + 1, jugador, dificultad, nivel.getFilas(), nivel.getColumnas());
        jugador.addTPSeguro();
        nivel.iniciar();
    }

    /**
     * Establece en false el atributo juegoPerdido
     */
    private void juegoPerdido() {
        enJuego = false;
    }

    /**
     * verifica se sigue en juego
     *
     * @return True si está en jeugo, False en caso contrio
     */

    public boolean estaEnJuego() {
        return enJuego;
    }

    /**
     * vDevuelve el Nivel actual
     *
     * @return Nivel
     */
    public Nivel getNivel() {
        return nivel;
    }

    /**
     * intenta jugar el turno en una direccion; si no quedan robots avanza de nivel; si arroja ColisionConJugadorException termina el juego.
     *
     * @param direccion Dirección del movimiento del jugador
     * @throws Exception Error inesperado
     */
    public void jugarTurno(int[] direccion) throws Exception {
        try {
            nivel.jugarTurno(direccion);
            if (nivel.noHayRobots()) avanzarNivel();
        } catch (ColisionConJugadorException e) {
            juegoPerdido();
        }
    }

    /**
     * Usa el tpSeguro en una fila-columnamoviendo al jugador a la posición especificada
     *
     * @param fila    Fila elegida por el jugador
     * @param columna Columna elegida por el jugador
     * @throws Exception Error inesperado
     */
    public void usarTPSeguro(int fila, int columna) throws Exception {
        jugador.usarTPseguro();
        usarTP(fila, columna);
    }

    /**
     * usa el TP a una fila-columna aleatorias dentro de las filas-columnas del tablero
     *
     * @throws Exception Error inesperado
     */
    public void usarTP() throws Exception {
        var fila = new Random().nextInt(nivel.getFilas());
        var columna = new Random().nextInt(nivel.getColumnas());
        usarTP(fila, columna);
    }

    /**
     * intenta jugar un turno en el nivel a fila-columna. Si no hay robots, avanza al siguiente nivel. Si se produce una colisión, se llama a juegoPerdido
     *
     * @param fila    Fila elegida por el jugador
     * @param columna Columna elegida por el jugador
     * @throws Exception Error inesperado
     */
    private void usarTP(int fila, int columna) throws Exception {
        try {
            nivel.jugarTurno(fila, columna);
            if (nivel.noHayRobots()) avanzarNivel();
        } catch (ColisionConJugadorException e) {
            juegoPerdido();
        }
    }
}