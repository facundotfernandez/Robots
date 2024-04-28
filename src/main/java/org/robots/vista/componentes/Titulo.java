package org.robots.vista.componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Titulo extends Label {

    /**
     * Inicializa los componentes del componente
     *
     * @param titulo Texto del componente
     */
    public Titulo(String titulo) {
        super(titulo);
        configurarEstilos();
    }

    /**
     * Configuración de estilos básicos del componente
     */
    void configurarEstilos() {
        setFont(Font.font("Canterell", FontWeight.BOLD, 18));
        setStyle("-fx-text-fill: #343A3B;");
        setAlignment(Pos.CENTER);
    }
}
