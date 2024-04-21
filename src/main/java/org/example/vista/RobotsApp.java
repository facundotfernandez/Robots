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

    String titulo;
    private Pane root;
    private Stage escenario;
    private Juego juego;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage escenario) {

        this.titulo = "Robots";
        var ventanaMenu = new VentanaMenuPrincipal(escenario, titulo);
        ventanaMenu.getFooter().getChildren().forEach(node -> {
            if (node instanceof Boton boton) {
                boton.setOnAction(event -> {
                    int userData = (int) boton.getUserData();
                    try {
                        iniciarJuego(userData);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
        this.root = ventanaMenu;
        this.escenario = escenario;
        escenario.setScene(new Scene(root, 1440, 768));
        configurarEstilos();

        escenario.show();
    }

    private void setCursor() {
        Image imagenCursor = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cursores/cursor.png")));
        escenario.getScene().setCursor(new ImageCursor(imagenCursor, 16, 16));
    }

    private void configurarEstilos() {
        escenario.setResizable(false);
        escenario.initStyle(StageStyle.UNDECORATED);

        setVentanaCerrable();
        setVentanaMovible();
        setCursor();
    }

    private void setVentanaCerrable() {
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                escenario.close();
            }
        });
    }

    private void iniciarJuego(int dificultad) throws Exception {
        this.juego = new Juego(dificultad);
        root = new VentanaJuego(escenario, titulo, 1, 0, juego.getNivel().getTablero());
        escenario.setScene(new Scene(root, 1440, 768));

        setCursor();
        setVentanaMovible();
        setVentanaCerrable();
    }

    private void setVentanaMovible() {
        root.setOnMousePressed(pressEvent -> {
            root.setOnMouseDragged(dragEvent -> {
                escenario.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                escenario.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    private void jugarTurno(int[] direccion) throws CeldaDesocupadaException, CeldaOcupadaException, ColisionConJugadorException {
        juego.jugarTurno(direccion);
    }

}