package org.example.modelo.jugabilidad;

import org.example.modelo.tablero.CeldaDesocupadaException;
import org.example.modelo.tablero.CeldaOcupadaException;
import org.example.modelo.unidades.Jugador;

import java.util.Random;

import static org.example.modelo.utilidades.Constantes.DIFICIL;
import static org.example.modelo.utilidades.Constantes.FACIL;

public class Juego {
    private final Jugador jugador;
    private final int dificultad;
    private Nivel nivel;
    private boolean enJuego;

    public Juego(int dificultad, int filas, int columnas) throws Exception {
        this.jugador = new Jugador((int) Math.ceil((((Math.pow(dificultad, 2) * 10) / Math.exp(dificultad)))));
        validarDificultad(dificultad);
        this.dificultad = dificultad;
        this.nivel = new Nivel(1, jugador, dificultad, filas, columnas);
        enJuego = true;
        nivel.iniciar();
    }

    private void validarDificultad(int dificultad) throws Exception {
        if (dificultad < FACIL || dificultad > DIFICIL) {
            throw new Exception("La dificultad no es válida");
        }
    }

    private void avanzarNivel() throws CeldaOcupadaException {
        this.nivel = new Nivel(nivel.getId() + 1, jugador, dificultad, nivel.getFilas(), nivel.getColumnas());
        jugador.addTPSeguro();
        nivel.iniciar();
    }

    private void juegoPerdido() {
        enJuego = false;
    }

    private void juegoGanado() {
        enJuego = false;
    }

    public boolean estaEnJuego() {
        return enJuego;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void jugarTurno(int[] direccion) throws CeldaDesocupadaException, CeldaOcupadaException, ColisionConJugadorException {
        if (nivel.hayRobots()) {
            try {
                nivel.jugarTurno(direccion);
                if (!nivel.hayRobots()) juegoGanado();
            } catch (ColisionConJugadorException e) {
                juegoPerdido();
            }
        } else avanzarNivel();
    }

    private void usarTP() throws CeldaDesocupadaException {
        var fila = new Random().nextInt(nivel.getFilas());
        var columna = new Random().nextInt(nivel.getColumnas());
        usarTP(fila, columna);
    }

    private void usarTPSeguro(int fila, int columna) throws CeldaDesocupadaException {
        try {
            jugador.usarTPseguro();
        } catch (IndexOutOfBoundsException _) {
        }
        usarTP(fila, columna);
    }

    private void usarTP(int fila, int columna) throws CeldaDesocupadaException {
        try {
            nivel.jugarTurno(fila, columna);
            if (!nivel.hayRobots()) juegoGanado();
        } catch (ColisionConJugadorException e) {
            juegoPerdido();
        }
    }
}