package org.robots.vista.contenedores;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.robots.vista.componentes.Boton;

import java.util.LinkedList;

public class BloqueDeBotones extends VBox {

    /**
     * Inicializa los componentes del contenedor
     *
     * @param botones Listado de botones
     */
    public BloqueDeBotones(LinkedList<Boton> botones) {
        super();
        var contenedor = new HBox();
        getChildren().add(contenedor);
        for (Boton boton : botones) contenedor.getChildren().add(boton);
        configurarEstilos(contenedor, botones);
    }


    /**
     * Configuración de estilos básicos del contenedor
     *
     * @param contenedor Contenedor al que pertenece cada botón
     * @param botones    Listado de botones
     */
    private void configurarEstilos(HBox contenedor, LinkedList<Boton> botones) {
        for (Boton boton : botones) {
            HBox.setHgrow(boton, Priority.ALWAYS);
            boton.setMaxWidth(Double.MAX_VALUE);
        }
        VBox.setVgrow(contenedor, Priority.ALWAYS);
    }
}
