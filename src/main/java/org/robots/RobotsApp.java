package org.robots;

import javafx.application.Application;
import javafx.stage.Stage;
import org.robots.vista.ventanas.Interfaz;

public class RobotsApp extends Application {

    /**
     * Se lanza la aplicación
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Inicializa la interfaz gráfica
     *
     * @param escenario Escenario principal
     */
    @Override
    public void start(Stage escenario) {
        new Interfaz(escenario);
    }
}