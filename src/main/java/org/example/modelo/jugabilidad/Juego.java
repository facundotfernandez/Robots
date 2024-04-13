package org.example.modelo.jugabilidad;

import org.example.modelo.tablero.Celda;
import org.example.modelo.tablero.CeldaInvalidaException;
import org.example.modelo.tablero.Tablero;
import org.example.modelo.unidades.Jugador;
import org.example.modelo.unidades.Personaje;
import org.example.modelo.unidades.Robot;
import org.example.modelo.utilidades.Direccion;

import java.util.LinkedList;
import java.util.Random;

public class Juego {
    private final Tablero<Personaje> tablero;
    private final int EXPLOSION = 0;
    private final LinkedList<Celda<Personaje>> celdasDeRobots;
    private Celda<Personaje> ubicacionJugador;
    private Nivel nivel_act;

    public Juego(int fila, int columna, String nombreJugador, int tpsSeguros) throws CeldaInvalidaException {
        this.tablero = new Tablero<Personaje>(fila, columna);
        this.nivel_act = new Nivel(1);
        tablero.ocuparCeldaCentral(new Jugador(nombreJugador, tpsSeguros));
        this.ubicacionJugador = tablero.getCeldaCentral();
        this.celdasDeRobots = new LinkedList<>();
    }

    private void actualizarJuego() throws CeldaInvalidaException {
        for (Celda<Personaje> celda : celdasDeRobots) {
            try {
                mover(celda, Direccion.getDireccion(new Random().nextInt(Direccion.values().length)));
            } catch (CeldaInvalidaException e) {
                mover(celda, Direccion.getDireccion(new Random().nextInt(Direccion.values().length)));
            }
        }
    }

    private void avanzarNivel() {
        this.nivel_act = new Nivel(nivel_act.getId() + 1);
    }

    private void mover(Celda<Personaje> origen, Direccion direccion) throws CeldaInvalidaException {
        mover(origen, tablero.getCelda(origen.getX() + direccion.getFila(), origen.getY() + direccion.getColumna()));
    }

    private boolean esRobot(Personaje personaje) {
        return personaje.getClass() == Robot.class;
    }

    private boolean esJugador(Personaje personaje) {
        return personaje.getClass() == Jugador.class;
    }

    private void mover(Celda<Personaje> origen, Celda<Personaje> destino) throws CeldaInvalidaException {
        Personaje ocupanteOrigen = origen.vaciar();
        try {
            destino.ocupar(ocupanteOrigen);
        } catch (CeldaInvalidaException e) {
            Personaje ocupanteDestino = destino.vaciar();
            if ((esJugador(ocupanteDestino)) || (esJugador(ocupanteOrigen) && esRobot(ocupanteDestino))) {
                juegoFinalizado();
            } else {
                destino.vaciar();
                if (esRobot(ocupanteOrigen) && esRobot(ocupanteDestino)) {
                    destino.ocupar(new Robot(EXPLOSION));
                } else {
                    destino.ocupar(ocupanteOrigen);
                }
            }
        }
        if (esRobot(destino.getOcupante())) {
            celdasDeRobots.add(destino);
        } else if (esJugador(destino.getOcupante())) {
            ubicacionJugador = destino;
        }
    }

    private void juegoFinalizado() {
        System.out.println("Juego finalizado");
    }
}
