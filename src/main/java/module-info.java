module atelier.atelier_de_origami {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens atelier.atelier_de_origami to javafx.fxml;
    exports atelier.atelier_de_origami;
}