package org.example.vista.ventanas;

import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.modelo.tablero.CeldaDesocupadaException;
import org.example.modelo.tablero.Tablero;
import org.example.modelo.unidades.Jugador;
import org.example.modelo.unidades.Personaje;
import org.example.modelo.unidades.Robot;
import org.example.modelo.utilidades.Direccion;
import org.example.vista.componentes.Boton;
import org.example.vista.contenedores.BarraDeTitulo;
import org.example.vista.contenedores.BloqueDeBotones;
import org.example.vista.contenedores.Encabezado;

import java.util.LinkedList;
import java.util.Objects;

public class VentanaJuego extends VBox {
    private static final int DIMENSION_CELDA = 32;
    private static final String[] ETIQUETAS_BOTONES = {"Teleportación Aleatoria", "Teleportación segura", "Esperar"};
    private static final String[] SUBTITULOS_BOTONES = {"", "Disponibles: 4", ""};
    private final String titulo;
    private final Tablero<Personaje> tablero;
    private final int filasTablero;
    private final int columnasTablero;
    Stage escenario;
    private GridPane seccionPrincipal;
    private int orientacionCursor;

    public VentanaJuego(Stage escenario, String titulo, int nivel, int puntaje, Tablero<Personaje> tablero) {
        this.titulo = titulo;
        this.tablero = tablero;
        this.escenario = escenario;
        escenario.setTitle(titulo);
        this.filasTablero = tablero.getFilas();
        this.columnasTablero = tablero.getColumnas();
        inicializarComponentes(nivel, puntaje);
    }

    private void inicializarComponentes(int nivel, int puntaje) {
        Encabezado header = new Encabezado(titulo, new String[]{"Nivel: " + nivel, "Puntaje: " + puntaje});

        GridPane seccionPrincipal = new GridPane();
        for (int fila = 0; fila < filasTablero; fila++) {
            for (int col = 0; col < columnasTablero; col++) {
                ImageView fondoCelda = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tablero/fondo_vacio.png"))));
                fondoCelda.setFitWidth(DIMENSION_CELDA);
                fondoCelda.setFitHeight(DIMENSION_CELDA);
                seccionPrincipal.add(fondoCelda, col, fila);

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
                    ImageView personaje = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tablero/" + tipoOcupante + ".png"))));
                    personaje.setFitWidth(DIMENSION_CELDA);
                    personaje.setFitHeight(DIMENSION_CELDA);
                    seccionPrincipal.add(personaje, col, fila);
                } catch (CeldaDesocupadaException _) {
                }
            }
        }

        BloqueDeBotones footer = crearBotonesAcciones();

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

            int centerRow = filasTablero / 2;
            int centerCol = (columnasTablero / 2) + corrimientoX;

            int orientacion = Direccion.getDireccion(Direccion.calcularDistancia(fila, col, centerRow, centerCol));
            if (orientacion != orientacionCursor) setCursor(orientacion);
            this.orientacionCursor = orientacion;
        });

        this.seccionPrincipal = seccionPrincipal;
        this.getChildren().addAll(new BarraDeTitulo(escenario), header, seccionPrincipal, footer);
    }

    private BloqueDeBotones crearBotonesAcciones() {
        LinkedList<Boton> botones = new LinkedList<>();
        for (int i = 0; i < ETIQUETAS_BOTONES.length; i++) {
            Boton boton = new Boton(ETIQUETAS_BOTONES[i], SUBTITULOS_BOTONES[i]);
            botones.add(boton);
        }

        return new BloqueDeBotones(botones);
    }

    private void setCursor(int tipo) {
        Image imagenCursor = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cursores/cursor-" + tipo + ".png")));
        seccionPrincipal.setCursor(new ImageCursor(imagenCursor, 16, 16));
    }

}
