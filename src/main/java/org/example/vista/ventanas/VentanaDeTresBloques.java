package org.example.vista.ventanas;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class VentanaDeTresBloques extends VBox {

    private final Pane header;
    private final Pane mainSection;
    private final Pane footer;

    public VentanaDeTresBloques(Pane header, Pane mainSection, Pane footer) {
        this.header = header;
        this.mainSection = mainSection;
        this.footer = footer;

        setSpacing(0);
        configurarDistribucion();
        getChildren().addAll(header, mainSection, footer);
    }

    public void configurarDistribucion() {
        VBox.setVgrow(mainSection, Priority.ALWAYS);
        setStyle("-fx-background-color: #DCDAD3");
        /*header.setStyle("-fx-background-color: #00ff78");
        mainSection.setStyle("-fx-background-color: #0033ff");
        footer.setStyle("-fx-background-color: #ff0000");*/
    }

    public Pane getHeader() {
        return header;
    }

    public Pane getMainSection() {
        return mainSection;
    }

    public Pane getFooter() {
        return footer;
    }
}
