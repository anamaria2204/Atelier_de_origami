package atelier.atelier_de_origami.controller;

import atelier.atelier_de_origami.service.Service;
import javafx.stage.Stage;

public class SignUpController {
    Service service;
    Stage stage;

    public void setService(Service service) {
        this.service = service;
    }
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
}
