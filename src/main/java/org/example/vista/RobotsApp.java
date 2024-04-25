package org.example.vista;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.modelo.jugabilidad.Juego;
import org.example.vista.componentes.Boton;
import org.example.vista.ventanas.VentanaJuego;
import org.example.vista.ventanas.VentanaMenuPrincipal;

import java.util.Objects;
import java.util.Optional;

import static org.example.vista.utilidades.Constantes.ALTO_VENTANA;
import static org.example.vista.utilidades.Constantes.ANCHO_VENTANA;


public class RobotsApp extends Application {

    private static Pane root;
    private static Stage escenario;
    private static Juego juego;

    public static void main(String[] args) {
        launch();
    }

    public static void setCursor() {
        Image imagenCursor = new Image(Objects.requireNonNull(RobotsApp.class.getResourceAsStream("/cursores/cursor.png")));
        escenario.getScene().setCursor(new ImageCursor(imagenCursor));
    }

    public static void iniciarJuego(int dificultad, int filas, int columnas) throws Exception {
        juego = new Juego(dificultad, filas, columnas);
        root = new VentanaJuego(escenario, juego.getNivel());
        escenario.setScene(new Scene(root, ANCHO_VENTANA, ALTO_VENTANA));
        configurarVentana();
    }

    private static void configurarVentana() {
        setCursor();
        setVentanaMovible();
    }

    private static void reiniciarJuego() {
        var escenarioAnterior = escenario;
        escenarioAnterior.close();
        RobotsApp.escenario = new Stage(StageStyle.UNDECORATED);
        iniciarMenuPrincipal();
        escenario.show();
    }

    private static void setVentanaMovible() {
        root.setOnMousePressed(pressEvent -> {
            root.setOnMouseDragged(dragEvent -> {
                escenario.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                escenario.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    public static void jugarTurno(int[] direccion) throws Exception {
        juego.jugarTurno(direccion);
        actualizarPantalla();
    }

    private static void mostrarJuegoPerdido() {
        Boton botonSalir = new Boton("SALIR");
        Boton botonReiniciar = new Boton("REINICIAR");

        ButtonType buttonTypeReiniciar = new ButtonType("REINICIAR", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeSalir = new ButtonType("SALIR", ButtonBar.ButtonData.NO);

        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.initStyle(StageStyle.UNDECORATED);
        alerta.setHeaderText("¿Querés comenzar de nuevo?");

        ButtonBar barraBotones = (ButtonBar) alerta.getDialogPane().lookup(".button-bar");
        barraBotones.getButtons().addAll(botonReiniciar, botonSalir);

        botonReiniciar.setOnAction(_ -> {
            alerta.setResult(buttonTypeReiniciar);
            alerta.close();
        });

        botonSalir.setOnAction(_ -> {
            alerta.setResult(buttonTypeSalir);
            alerta.close();
        });

        Optional<ButtonType> result = alerta.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == buttonTypeSalir) {
                System.exit(0);
            } else if (buttonType == buttonTypeReiniciar) {
                RobotsApp.reiniciarJuego();
            }
        });
    }

    public static void iniciarMenuPrincipal() {
        root = new VentanaMenuPrincipal(escenario);
        escenario.setScene(new Scene(root, ANCHO_VENTANA, ALTO_VENTANA));
        configurarEstilos();
    }

    private static void configurarEstilos() {
        escenario.setResizable(false);
        escenario.initStyle(StageStyle.UNDECORATED);
        configurarVentana();
    }

    public static void usarTP() {
        try {
            juego.usarTP();
            actualizarPantalla();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void usarTP(int fila, int col) {
        try {
            juego.usarTPSeguro(fila, col);
            actualizarPantalla();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void actualizarPantalla() {
        root = new VentanaJuego(escenario, juego.getNivel());
        escenario.setScene(new Scene(root, ANCHO_VENTANA, ALTO_VENTANA));
        configurarVentana();
        if (!juego.estaEnJuego()) mostrarJuegoPerdido();
    }

    @Override
    public void start(Stage escenario) {
        RobotsApp.escenario = escenario;
        iniciarMenuPrincipal();
        escenario.show();
    }

}