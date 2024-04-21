package org.example.vista.contenedores;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.vista.componentes.Eleccion;

public class BloqueDeElecciones extends HBox {

    public void agregarGrupo(String labelText, Eleccion<?> eleccionMultiple) {
        Label label = new Label(labelText);
        HBox.setHgrow(label, Priority.NEVER);
        HBox.setHgrow(eleccionMultiple, Priority.ALWAYS);
        label.setFont(Font.font("Canterell", FontWeight.BOLD, 16));
        label.setAlignment(Pos.CENTER);

        VBox container = new VBox();
        container.getChildren().addAll(eleccionMultiple, label);
        container.setAlignment(Pos.CENTER);
        HBox.setHgrow(container, Priority.ALWAYS);

        this.getChildren().add(container);
        configurarEstilos();
    }

    private void configurarEstilos() {
        setSpacing(10);
        setStyle("-fx-background-color: #E9E9E9; -fx-border-color: #111");
    }
}