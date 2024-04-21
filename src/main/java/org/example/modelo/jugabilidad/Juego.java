package org.example.modelo.jugabilidad;

import org.example.modelo.tablero.CeldaDesocupadaException;
import org.example.modelo.tablero.CeldaOcupadaException;
import org.example.modelo.unidades.Jugador;

import java.util.Random;

import static org.example.modelo.utilidades.Constantes.*;

public class Juego {
    private final Jugador jugador;
    private final int dificultad;
    private Nivel nivel;
    private boolean enJuego;

    public Juego(int dificultad) throws Exception {
        this.jugador = new Jugador((int) Math.ceil((((Math.pow(dificultad, 2) * 10) / Math.exp(dificultad)))));
        validarDificultad(dificultad);
        this.dificultad = dificultad;
        this.nivel = new Nivel(1, jugador, dificultad);
        enJuego = true;
        nivel.iniciar();
    }

    private void validarDificultad(int dificultad) throws Exception {
        if (dificultad < FACIL || dificultad > DIFICIL) {
            throw new Exception("La dificultad no es válida");
        }
    }

    private void avanzarNivel() throws CeldaOcupadaException {
        this.nivel = new Nivel(nivel.getId() + 1, jugador, dificultad);
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

    private void usarTP() throws CeldaOcupadaException, CeldaDesocupadaException, ColisionConJugadorException {
        var fila = new Random().nextInt(nivel.getFilas());
        var columna = new Random().nextInt(nivel.getColumnas());

        try {
            nivel.mover(jugador, new int[]{fila, columna});
            jugador.mover(fila, columna);
            nivel.moverRobots(jugador.getUbicacion());
            if (!nivel.hayRobots()) juegoGanado();
        } catch (ColisionConJugadorException e) {
            juegoPerdido();
        }
    }

    private void usarTPSeguro(int[] direccion) throws CeldaOcupadaException, CeldaDesocupadaException, ColisionConJugadorException {
        jugador.usarTPseguro();
        nivel.ubicar(jugador, direccion[0], direccion[1]);
        nivel.moverRobots(jugador.getUbicacion());
        if (!nivel.hayRobots()) juegoGanado();
    }
}