package org.example.vista.componentes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Titulo extends Label {

    public Titulo(String titulo) {
        super(titulo);
        configurarEstilos();
    }

    void configurarEstilos() {
        setFont(Font.font("Canterell", FontWeight.BOLD, 18));
        setStyle("-fx-text-fill: #343A3B;");
        setPadding(new Insets(0, 10, 0, 0));
        setAlignment(Pos.CENTER);
    }
}
