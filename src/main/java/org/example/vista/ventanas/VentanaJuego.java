package org.example.vista.ventanas;

import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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

import static org.example.modelo.utilidades.Direccion.CENTRO;
import static org.example.vista.utilidades.Constantes.*;

public class VentanaJuego extends VBox {
    private static final int DIMENSION_CELDA = 32;
    private final Tablero<Personaje> tablero;
    private final Jugador jugador;
    private final int filasTablero;
    private final int columnasTablero;
    private final int tpSeguros;
    Stage escenario;
    private GridPane seccionPrincipal;
    private int orientacionCursor;
    private boolean esperaEleccionCelda;

    public VentanaJuego(Stage escenario, Nivel nivel) {
        this.tablero = nivel.getTablero();
        this.escenario = escenario;
        escenario.setTitle(TITULO);
        this.filasTablero = tablero.getFilas();
        this.columnasTablero = tablero.getColumnas();
        this.jugador = nivel.getJugador();
        this.tpSeguros = nivel.getTPSeguros();
        this.esperaEleccionCelda = false;
        inicializarComponentes(nivel.getId(), nivel.getPuntaje(), tpSeguros);
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

        VBox.setVgrow(seccionPrincipal, Priority.ALWAYS);
        seccionPrincipal.setAlignment(Pos.CENTER);
        seccionPrincipal.setStyle("-fx-background-color: #7D9598");
        setSpacing(0);

        setCursor();
        seccionPrincipal.setOnMouseMoved(event -> {
            int orientacion = Direccion.getDireccion(calcularDistancia(event));
            if (orientacion != orientacionCursor) {
                setCursor(orientacion);
                this.orientacionCursor = orientacion;
            }
        });

        this.seccionPrincipal = seccionPrincipal;
        this.getChildren().addAll(new BarraDeTitulo(escenario), header, seccionPrincipal, footer);
        configurarControladores();
    }

    private int[] calcularDistancia(MouseEvent event) {
        int fila = (int) (event.getY() / DIMENSION_CELDA);
        int col = (int) (event.getX() / DIMENSION_CELDA);
        int corrimientoY = (int) ((seccionPrincipal.getHeight() - filasTablero * DIMENSION_CELDA) / (2 * DIMENSION_CELDA));
        int corrimientoX = (int) ((getWidth() - columnasTablero * DIMENSION_CELDA) / (2 * DIMENSION_CELDA));

        int filaCentro = jugador.getFila() + corrimientoY;
        int colCentro = jugador.getColumna() + corrimientoX;

        return Direccion.calcularDistancia(filaCentro, colCentro, fila, col);
    }

    private void configurarControladores() {
        configurarControladorJugarTurno();
    }

    private void configurarControladorJugarTurno() {
        seccionPrincipal.setOnMouseClicked(event -> {
            try {
                if (esperaEleccionCelda) {
                    esperaEleccionCelda = false;
                    int fila = (int) (event.getY() / DIMENSION_CELDA);
                    int col = (int) (event.getX() / DIMENSION_CELDA);
                    RobotsApp.usarTP(fila, col);
                } else {
                    RobotsApp.jugarTurno(calcularDistancia(event));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setOnKeyReleased(event -> {
            KeyCode tecla = event.getCode();
            esperaEleccionCelda = false;
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
            setControladorBoton(boton, i);
            botones.add(boton);
        }
        return new BloqueDeBotones(botones);
    }

    private void setControladorBoton(Boton boton, int i) {
        if (i == 0) {
            boton.setOnAction(event -> {
                RobotsApp.usarTP();
            });
        } else if (i == 1) {
            boton.setOnAction(event -> {
                esperaEleccionCelda = true;
            });
            if (tpSeguros == 0) boton.setDisable(true);
        } else {
            boton.setOnAction(event -> {
                try {
                    RobotsApp.jugarTurno(CENTRO.getDireccion());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void setCursor(int tipo) {
        Image imagenCursor = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cursores/cursor-" + tipo + ".png")));
        seccionPrincipal.setCursor(new ImageCursor(imagenCursor, 16, 16));
    }

    private void setCursor() {
        Image imagenCursor = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cursores/cursor.png")));
        setCursor(new ImageCursor(imagenCursor, 0, 0));
    }

}
