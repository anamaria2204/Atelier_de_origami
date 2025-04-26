module atelier.atelier_de_origami {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.sql;
    opens atelier.atelier_de_origami to javafx.fxml;
    exports atelier.atelier_de_origami;
}