package org.example.vista.componentes;

import javafx.stage.Stage;

public class BotonCerrar extends Boton {

    /**
     * Inicializa los componentes del componente
     *
     * @param escenario Escenario principal
     */
    public BotonCerrar(Stage escenario) {
        super("X");
        configurarEstilos(escenario);
    }

    /**
     * Configuración de estilos básicos del componente y
     * Configuración del controlador del componente que cierra el escenario dado
     *
     * @param escenario Escenario principal
     */
    private void configurarEstilos(Stage escenario) {
        setOnAction(e -> escenario.close());
        setPrefSize(50, 50);
    }
}
