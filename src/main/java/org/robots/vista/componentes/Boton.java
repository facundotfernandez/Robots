package org.robots.vista.componentes;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Boton extends Button {

    /**
     * Inicializa los componentes del componente
     *
     * @param etiqueta Etiqueta del Botón
     */
    public Boton(String etiqueta) {
        super(etiqueta);
        configurarEstilos();
    }

    /**
     * Configuración de estilos básicos del componente
     */
    private void configurarEstilos() {
        Font.loadFont(getClass().getResourceAsStream("Canterell-Regular.ttf"), 10);

        setPrefHeight(64);
        setStyle("-fx-background-color: #E9E9E9; -fx-background-radius: 0px; -fx-text-alignment: center; -fx-font-size: 16px; -fx-text-fill: #343A3B; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        setFont(Font.font("Canterell", FontWeight.BOLD, 24));
        setOnMouseEntered(e -> setStyle("-fx-background-color: #D2D2D2; -fx-background-radius: 0px; -fx-text-alignment: center; -fx-font-size: 16px;"));
        setOnMouseExited(e -> setStyle("-fx-background-color: #E9E9E9; -fx-background-radius: 0px; -fx-text-alignment: center; -fx-font-size: 16px;"));
        setOnMousePressed(e -> setStyle("-fx-background-color: #808080; -fx-background-radius: 0px; -fx-text-alignment: center; -fx-font-size: 16px;"));
        setOnMouseReleased(e -> setStyle("-fx-background-color: #E9E9E9; -fx-background-radius: 0px; -fx-text-alignment: center; -fx-font-size: 16px;"));
    }

}