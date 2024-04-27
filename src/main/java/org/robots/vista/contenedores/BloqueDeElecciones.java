package org.robots.vista.contenedores;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.robots.vista.componentes.Eleccion;

public class BloqueDeElecciones extends HBox {


    /**
     * Inicializa un grupo que contiene Etiqueta y una Eleccion
     *
     * @param etiqueta         Etiqueta de cada contenedor
     * @param eleccionMultiple Bloque de elección con opciones múltiples
     */
    public void agregarGrupo(String etiqueta, Eleccion<?> eleccionMultiple) {
        Label label = new Label(etiqueta);
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

    /**
     * Configuración de estilos básicos del contenedor
     */
    private void configurarEstilos() {
        setSpacing(10);
        setStyle("-fx-background-color: #E9E9E9; -fx-border-color: #111");
    }
}