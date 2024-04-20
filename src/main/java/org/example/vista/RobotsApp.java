package org.example.vista;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.modelo.jugabilidad.Juego;
import org.example.vista.ventanas.VentanaMenuPrincipal;

import java.util.Objects;


public class RobotsApp extends Application {

    String titulo;
    private Scene escenaDificultad;
    private Scene escenaJuego;
    private Juego juego;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage escenario) {

        this.titulo = "Robots";
        VentanaMenuPrincipal ventanaInicio = new VentanaMenuPrincipal(escenario, titulo);
        Image imagenCursor = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cursores/cursor.png")));
        ventanaInicio.setCursor(new ImageCursor(imagenCursor, 16, 16).getImage());
        escenario.setResizable(false);
        escenario.initStyle(StageStyle.UNDECORATED);
        escenario.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                escenario.close();
            }
        });
        escenario.show();
    }

}