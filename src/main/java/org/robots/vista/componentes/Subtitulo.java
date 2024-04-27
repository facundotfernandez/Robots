package org.robots.vista.componentes;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Subtitulo extends Label {

    /**
     * Inicializa los componentes del componente
     *
     * @param subtitulo Texto del componente
     */
    public Subtitulo(String subtitulo) {
        super(subtitulo);
        configurarEstilos();
    }

    /**
     * Configuración de estilos básicos del componente
     */
    private void configurarEstilos() {
        setFont(Font.font("Canterell", 14));
        setStyle("-fx-text-fill: #808080;");
    }
}
