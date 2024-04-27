package org.robots.vista.componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class BotonComenzar extends Button {

    /**
     * Inicializa los componentes del componente
     */
    public BotonComenzar() {
        super("COMENZAR JUEGO");
        configurarEstilos();
    }

    /**
     * Configuración de estilos básicos del componente
     */
    private void configurarEstilos() {
        setStyle("-fx-background-color: linear-gradient(to right, #ff7f0e, #d62728); -fx-text-fill: white; -fx-alignment: center; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 5px;");

        setOnMousePressed(_ -> {
            setStyle("-fx-background-color: linear-gradient(to right, #d62728, #ff7f0e); -fx-text-fill: white; -fx-alignment: center; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 5px;");
        });

        setOnMouseReleased(_ -> {
            setStyle("-fx-background-color: linear-gradient(to right, #ff7f0e, #d62728); -fx-text-fill: white; -fx-alignment: center; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 5px;");
        });
        setAlignment(Pos.CENTER);
    }
}
