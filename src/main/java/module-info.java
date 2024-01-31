module tsbfrc.segunda_entrega {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens tsbfrc.clases to javafx.fxml;
    exports tsbfrc.clases;
}