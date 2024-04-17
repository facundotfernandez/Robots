package org.example.modelo.jugabilidad;

import org.example.modelo.tablero.CeldaInvalidaException;
import org.example.modelo.unidades.Jugador;
import org.example.modelo.unidades.Personaje;
import org.example.modelo.unidades.Robot;
import org.example.modelo.utilidades.Direccion;

public class Juego {
    private final Jugador jugador;
    private Nivel nivel;

    public Juego(int filas, int columnas, String nombreJugador, int tpsSeguros) throws CeldaInvalidaException {
        this.nivel = new Nivel(1);
        this.jugador = new Jugador(nombreJugador, tpsSeguros);
        iniciarJuego(nombreJugador, tpsSeguros);
    }

    private void avanzarNivel() {
        this.nivel = new Nivel(nivel.getId() + 1);
    }

    private boolean esRobot(Personaje personaje) {
        return personaje.getClass() == Robot.class;
    }

    private boolean esJugador(Personaje personaje) {
        return personaje.getClass() == Jugador.class;
    }

    /*private void mover(Celda<Personaje> origen, Celda<Personaje> destino) throws CeldaInvalidaException {
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
                    destino.ocupar(new Robot(EXPLOSION);
                } else {
                    destino.ocupar(ocupanteOrigen);
                }
            }
        } if (esRobot(destino.getOcupante())) {
            celdasDeRobots.add(destino);
        } else if (esJugador(destino.getOcupante())) {
            ubicacionJugador = destino;
        }
    }

    public void mover2(Celda<Personaje> origen, Direccion direccion) throws Exception {
        Celda<Personaje> destino = tablero.getCelda(origen.getX() + direccion.getFila(), origen.getY() + direccion.getColumna());

        boolean movimientoExitoso = tablero.moverPersonaje(origen, destino);

        if (!movimientoExitoso) {
            Personaje ocupanteOrigen = origen.getOcupante();
            Personaje ocupanteDestino = destino.getOcupante();

            if ((esJugador(ocupanteOrigen) && esRobot(ocupanteDestino)) || (esJugador(ocupanteDestino) && esRobot(ocupanteOrigen))) {
                juegoFinalizado();
                return;
            }

            if (esRobot(ocupanteOrigen) && esRobot(ocupanteDestino)) {
                tablero.explotarRobot(destino.getX(), destino.getY());
                celdasDeRobots.add(destino);
                return;
            }
            if (esRobot(destino.getOcupante())) {
                celdasDeRobots.add(destino);
            } else if (esJugador(destino.getOcupante())) {
                ubicacionJugador = destino;
            }

        }
    }*/

    private void juegoFinalizado() {
        System.out.println("Juego finalizado");
    }

    private void iniciarJuego(String nombreJugador, int tpsSeguros) throws CeldaInvalidaException {
        nivel.ubicarEnCentro(jugador);

        for (Robot robot : nivel.getRobots()) {
            nivel.ubicar(robot);
        }
    }

    private void jugarTurno(Jugador jugador, int[] direccionJugador) {
        var direccion = Direccion.getDireccion(direccionJugador);
        nivel.mover(jugador.getUbicacion(), direccion);
        jugador.mover(direccionJugador);

        nivel.moverRobots(jugador.getUbicacion());
    }
}