package org.example.vista;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.modelo.jugabilidad.ColisionConJugadorException;
import org.example.modelo.jugabilidad.Juego;
import org.example.modelo.tablero.CeldaDesocupadaException;
import org.example.modelo.tablero.CeldaOcupadaException;
import org.example.vista.componentes.Boton;
import org.example.vista.ventanas.VentanaJuego;
import org.example.vista.ventanas.VentanaMenuPrincipal;

import java.util.Objects;


public class RobotsApp extends Application {

    static String titulo;
    private static Pane root;
    private static Stage escenario;
    private static Juego juego;

    public static void main(String[] args) {
        launch();
    }

    private static void setCursor() {
        Image imagenCursor = new Image(Objects.requireNonNull(RobotsApp.class.getResourceAsStream("/cursores/cursor.png")));
        escenario.getScene().setCursor(new ImageCursor(imagenCursor));
    }

    public static void iniciarJuego(int dificultad, int filas, int columnas) throws Exception {
        juego = new Juego(dificultad, filas, columnas);
        root = new VentanaJuego(escenario, titulo, 1, 0, juego.getNivel().getTablero());
        escenario.setScene(new Scene(root, 1440, 818));

        setCursor();
        setVentanaMovible();
        setVentanaCerrable();
    }

    private static void setVentanaMovible() {
        root.setOnMousePressed(pressEvent -> {
            root.setOnMouseDragged(dragEvent -> {
                escenario.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                escenario.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    private static void setVentanaCerrable() {
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                escenario.close();
            }
        });
    }

    @Override
    public void start(Stage escenario) {

        titulo = "Robots";
        Boton botonComenzarJuego = new Boton("COMENZAR\nJUEGO");
        root = new VentanaMenuPrincipal(escenario, titulo, botonComenzarJuego);
        RobotsApp.escenario = escenario;
        escenario.setScene(new Scene(root, 1440, 818));
        configurarEstilos();

        escenario.show();
    }

    private void configurarEstilos() {
        escenario.setResizable(false);
        escenario.initStyle(StageStyle.UNDECORATED);

        setVentanaCerrable();
        setVentanaMovible();
        setCursor();
    }

    private void jugarTurno(int[] direccion) throws CeldaDesocupadaException, CeldaOcupadaException, ColisionConJugadorException {
        juego.jugarTurno(direccion);
    }

}