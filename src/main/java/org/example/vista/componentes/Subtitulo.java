package org.example.vista.componentes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Subtitulo extends Label {
    public Subtitulo(String subtitulo) {
        super(subtitulo);
        configurarEstilos();
    }

    private void configurarEstilos() {
        setFont(Font.font("Canterell", 14));
        setStyle("-fx-text-fill: #808080;");
    }
}
