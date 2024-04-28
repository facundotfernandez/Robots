package org.robots.vista.contenedores;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.robots.vista.componentes.Boton;
import org.robots.vista.componentes.BotonCerrar;
import org.robots.vista.componentes.BotonMinimizar;
import org.robots.vista.componentes.Icono;

public class BarraDeTitulo extends HBox {

    /**
     * Inicializa los componentes del contenedor
     *
     * @param escenario Escenario principal
     */
    public BarraDeTitulo(Stage escenario) {
        Icono icono = new Icono("/tablero/icono.png", 32, 32);
        BotonCerrar botonCerrar = new BotonCerrar(escenario);
        BotonMinimizar botonMinimizar = new BotonMinimizar(escenario);
        configurarEstilos(new HBox(), icono, botonCerrar, botonMinimizar);
    }

    /**
     * Configuración de estilos básicos del contenedor
     *
     * @param contenedorVacio Contenedor vacío que ocupa espacio disponible restante
     * @param icono           Icono principal ventana
     * @param botonCerrar     Botón que permite cerrar una escena o escenario
     * @param botonMinimizar  Botón que permite minimizar una escena o escenario
     */
    private void configurarEstilos(HBox contenedorVacio, Icono icono, Boton botonCerrar, Boton botonMinimizar) {
        setHgrow(contenedorVacio, Priority.ALWAYS);
        setStyle("-fx-background-color: #E9E9E9");
        setSpacing(0);
        setPrefHeight(64);

        getChildren().addAll(icono, contenedorVacio, botonMinimizar, botonCerrar);
    }
}
