package org.example.vista.ventanas;

import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.modelo.jugabilidad.Nivel;
import org.example.modelo.tablero.CeldaDesocupadaException;
import org.example.modelo.tablero.Tablero;
import org.example.modelo.unidades.Jugador;
import org.example.modelo.unidades.Personaje;
import org.example.modelo.unidades.Robot;
import org.example.modelo.utilidades.Direccion;
import org.example.vista.RobotsApp;
import org.example.vista.componentes.Boton;
import org.example.vista.contenedores.BarraDeTitulo;
import org.example.vista.contenedores.BloqueDeBotones;
import org.example.vista.contenedores.Encabezado;

import java.util.LinkedList;
import java.util.Objects;

import static org.example.vista.utilidades.Constantes.*;

public class VentanaJuego extends VBox {
    private static final int DIMENSION_CELDA = 32;
    private final Tablero<Personaje> tablero;
    private final Jugador jugador;
    private final int filasTablero;
    private final int columnasTablero;
    Stage escenario;
    private GridPane seccionPrincipal;
    private int orientacionCursor;

    public VentanaJuego(Stage escenario, Nivel nivel) {
        this.tablero = nivel.getTablero();
        this.escenario = escenario;
        escenario.setTitle(TITULO);
        this.filasTablero = tablero.getFilas();
        this.columnasTablero = tablero.getColumnas();
        this.jugador = nivel.getJugador();
        inicializarComponentes(nivel.getId(), nivel.getPuntaje(), nivel.getTPSeguros());
    }

    private void agregarImagenCelda(GridPane contenedor, String ruta, int x, int y) {
        ImageView imagen = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta))));
        imagen.setFitHeight(DIMENSION_CELDA);
        imagen.setFitWidth(DIMENSION_CELDA);
        contenedor.add(imagen, x, y);
    }

    private void inicializarComponentes(int nivel, int puntaje, int tpSeguros) {
        Encabezado header = new Encabezado(new String[]{ETIQUETAS_ESTADO_JUEGO[0] + " " + nivel, ETIQUETAS_ESTADO_JUEGO[1] + " " + puntaje});

        GridPane seccionPrincipal = new GridPane();
        for (int fila = 0; fila < filasTablero; fila++) {
            for (int col = 0; col < columnasTablero; col++) {
                agregarImagenCelda(seccionPrincipal, "/tablero/fondo_vacio.png", col, fila);
                try {
                    Personaje ocupante = tablero.getOcupante(fila, col);
                    String tipoOcupante = "";
                    try {
                        Jugador jugador = (Jugador) ocupante;
                        tipoOcupante = "jugador";
                    } catch (ClassCastException _) {
                        try {
                            Robot robot = (Robot) ocupante;
                            tipoOcupante = "robot-" + robot.getMultiplicador();
                        } catch (ClassCastException _) {
                        }
                    }
                    agregarImagenCelda(seccionPrincipal, "/tablero/" + tipoOcupante + ".png", col, fila);
                } catch (CeldaDesocupadaException _) {
                }
            }
        }

        BloqueDeBotones footer = crearBotonesAcciones(tpSeguros);

        seccionPrincipal.setPrefHeight(filasTablero * DIMENSION_CELDA);
        seccionPrincipal.setAlignment(Pos.CENTER);
        seccionPrincipal.setStyle("-fx-background-color: #7D9598");
        setSpacing(0);

        seccionPrincipal.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            int fila = (int) (mouseY / DIMENSION_CELDA);
            int col = (int) (mouseX / DIMENSION_CELDA);
            int corrimientoX = (int) ((getWidth() - columnasTablero * DIMENSION_CELDA) / (2 * DIMENSION_CELDA));

            int filaCentro = jugador.getFila();
            int centerCol = jugador.getColumna() + corrimientoX;

            int orientacion = Direccion.getDireccion(Direccion.calcularDistancia(filaCentro, centerCol, fila, col));
            if (orientacion != orientacionCursor) {
                setCursor(orientacion);
                this.orientacionCursor = orientacion;
            }
        });

        this.seccionPrincipal = seccionPrincipal;
        this.getChildren().addAll(new BarraDeTitulo(escenario), header, seccionPrincipal, footer);

        configurarControladores();
    }

    private void configurarControladores() {
        configurarControladorJugarTurno();
    }

    private void configurarControladorJugarTurno() {
        seccionPrincipal.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            int fila = (int) (mouseY / DIMENSION_CELDA);
            int col = (int) (mouseX / DIMENSION_CELDA);
            int corrimientoX = (int) ((getWidth() - columnasTablero * DIMENSION_CELDA) / (2 * DIMENSION_CELDA));

            int filaCentro = jugador.getFila();
            int centerCol = jugador.getColumna() + corrimientoX;

            try {
                RobotsApp.jugarTurno(Direccion.calcularDistancia(filaCentro, centerCol, fila, col));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setOnKeyReleased(event -> {
            KeyCode tecla = event.getCode();
            if (CONTROLES.containsKey(tecla)) {
                try {
                    RobotsApp.jugarTurno(CONTROLES.get(tecla).getDireccion());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private BloqueDeBotones crearBotonesAcciones(int tpSeguros) {
        LinkedList<Boton> botones = new LinkedList<>();
        for (int i = 0; i < ETIQUETAS_BOTONES_JUEGO.length; i++) {
            Boton boton = new Boton(ETIQUETAS_BOTONES_JUEGO[i] + ((i == 1) ? (" " + tpSeguros) : ""));
            botones.add(boton);
        }

        return new BloqueDeBotones(botones);
    }

    private void setCursor(int tipo) {
        Image imagenCursor = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cursores/cursor-" + tipo + ".png")));
        seccionPrincipal.setCursor(new ImageCursor(imagenCursor, 16, 16));
    }

}
