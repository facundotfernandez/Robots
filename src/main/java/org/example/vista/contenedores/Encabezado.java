package org.example.vista.contenedores;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.vista.componentes.Subtitulo;

public class Encabezado extends VBox {
    private final Label titulo;
    private final HBox containerSubtitulos;

    public Encabezado(String titulo) {
        this.titulo = new Label(titulo);
        getChildren().add(this.titulo);

        this.containerSubtitulos = new HBox();
        configurarEstilos();
    }

    public Encabezado(String titulo, String[] subtitulos) {
        this.titulo = new Label(titulo);
        getChildren().add(this.titulo);

        this.containerSubtitulos = new HBox();
        getChildren().add(containerSubtitulos);

        for (String subtitulo : subtitulos) {
            containerSubtitulos.getChildren().add(new Subtitulo(subtitulo));
        }

        configurarEstilos();
    }

    private void configurarEstilos() {
        Font.loadFont(getClass().getResourceAsStream("Canterell-Regular.ttf"), 10);

        titulo.setFont(Font.font("Canterell", FontWeight.BOLD, 18));
        titulo.setStyle("-fx-text-fill: #343A3B;");
        titulo.setPadding(new Insets(0, 10, 0, 0));

        titulo.setAlignment(Pos.CENTER);
        containerSubtitulos.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);

        setSpacing(5);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #E9E9E9;");
    }
}