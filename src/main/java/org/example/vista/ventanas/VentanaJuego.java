package org.example.vista.ventanas;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.modelo.tablero.Tablero;
import org.example.modelo.unidades.Personaje;
import org.example.modelo.utilidades.Direccion;
import org.example.vista.componentes.Boton;
import org.example.vista.contenedores.BloqueDeBotones;
import org.example.vista.contenedores.Encabezado;

import java.util.LinkedList;
import java.util.Objects;

public class VentanaJuego extends VBox {
    private static final int CELL_SIZE = 32;
    private static final String[] etiquetasBotones = {"Teleportación Aleatoria", "Teleportación segura", "Esperar"};
    private final String titulo;
    private final Tablero<Personaje> tablero;
    private Encabezado header;
    private GridPane seccionPrincipal;
    private BloqueDeBotones footer;
    private int orientacionCursor;
    Stage escenario;

    public VentanaJuego(Stage escenario, String titulo, int nivel, int puntaje, Tablero<Personaje> tablero) {
        this.titulo = titulo;
        this.tablero = tablero;
        this.escenario = escenario;
        escenario.setTitle(titulo);
        inicializarComponentes(nivel, puntaje);
    }

    private void inicializarComponentes(int nivel, int puntaje) {
        Encabezado header = new Encabezado(escenario, titulo, new String[]{"Nivel: " + nivel, "Puntaje: " + puntaje});

        GridPane seccionPrincipal = new GridPane();
        for (int row = 0; row < tablero.getFilas(); row++) {
            for (int col = 0; col < tablero.getColumnas(); col++) {
                ImageView fondoCelda = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tablero/fondo_vacio.bmp"))));
                fondoCelda.setFitWidth(CELL_SIZE);
                fondoCelda.setFitHeight(CELL_SIZE);
                seccionPrincipal.add(fondoCelda, col, row);
            }
        }

        BloqueDeBotones footer = crearBotonesAcciones();

        seccionPrincipal.setPrefHeight(tablero.getFilas() * CELL_SIZE);
        setStyle("-fx-background-color: #DCDAD3");
        setSpacing(0);

        seccionPrincipal.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            int row = (int) (mouseY / CELL_SIZE);
            int col = (int) (mouseX / CELL_SIZE);

            int centerRow = tablero.getFilas() / 2;
            int centerCol = tablero.getColumnas() / 2;

            int orientacion = Direccion.getDireccion(Direccion.calcularDistancia(row, col, centerRow, centerCol));
            if (orientacion != orientacionCursor) setCursor(orientacion);
            this.orientacionCursor = orientacion;
        });

        this.header = header;
        this.seccionPrincipal = seccionPrincipal;
        this.footer = footer;
        this.getChildren().addAll(header, seccionPrincipal, footer);
    }

    private BloqueDeBotones crearBotonesAcciones() {
        LinkedList<Boton> botones = new LinkedList<>();
        for (String etiqueta : etiquetasBotones) {
            Boton boton = new Boton(etiqueta);
            botones.add(boton);
        }

        return new BloqueDeBotones(botones);
    }

    public Encabezado getHeader() {
        return header;
    }

    public GridPane getSeccionPrincipal() {
        return seccionPrincipal;
    }

    public BloqueDeBotones getFooter() {
        return footer;
    }

    private void setCursor(int tipo) {
        Image imagenCursor = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cursores/cursor-" + tipo + ".png")));
        seccionPrincipal.setCursor(new ImageCursor(imagenCursor, 16, 16));
    }

}
