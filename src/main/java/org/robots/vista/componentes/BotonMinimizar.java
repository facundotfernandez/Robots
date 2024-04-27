package org.robots.vista.componentes;

import javafx.stage.Stage;

public class BotonMinimizar extends Boton {

    /**
     * Inicializa los componentes del componente
     *
     * @param escenario Escenario principal
     */
    public BotonMinimizar(Stage escenario) {
        super("_");
        configurarEstilos(escenario);
    }

    /**
     * Configuración de estilos básicos del componente y
     * Configuración del controlador del componente que minimiza el escenario dado
     *
     * @param escenario Escenario principal
     */
    private void configurarEstilos(Stage escenario) {
        setOnAction(e -> escenario.setIconified(true));
        setPrefSize(50, 50);
    }
}
