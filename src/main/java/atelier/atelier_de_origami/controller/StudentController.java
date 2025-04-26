package atelier.atelier_de_origami.controller;

import atelier.atelier_de_origami.domain.Student;
import atelier.atelier_de_origami.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StudentController {
    Service service;
    Stage stage;
    Student student;

    @FXML
    Label labelStudent;

    public void setService(Service service, Student student) {
        this.service = service;
        this.student = student;
        initModel();
    }
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    private void initModel() {
        labelStudent.setText("Hello " + student.getFirstname() + " " + student.getLastname() + "! <3");
    }

}
