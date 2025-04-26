package atelier.atelier_de_origami;

import atelier.atelier_de_origami.controller.LogInController;
import atelier.atelier_de_origami.domain.*;
import atelier.atelier_de_origami.domain.validator.*;
import atelier.atelier_de_origami.repository.ICourseRepo;
import atelier.atelier_de_origami.repository.IStudentRepo;
import atelier.atelier_de_origami.repository.ITeacherRepo;
import atelier.atelier_de_origami.repository.database.CourseRepo;
import atelier.atelier_de_origami.repository.database.StudentRepo;
import atelier.atelier_de_origami.repository.database.TeacherRepo;
import atelier.atelier_de_origami.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.security.auth.login.LoginContext;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

public class Main extends Application {

    Service service;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Properties prop = new Properties();
        try{
            prop.load(new FileReader("bd.config"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StudentValidator studentValidator = (StudentValidator) ValidatorFactory.getInstance().createValidator(ValidatorStrategy.Student);
        TeacherValidator teacherValidator = (TeacherValidator) ValidatorFactory.getInstance().createValidator(ValidatorStrategy.Teacher);
        CourseValidator courseValidator = (CourseValidator) ValidatorFactory.getInstance().createValidator(ValidatorStrategy.Course);

        IStudentRepo studentRepo = new StudentRepo(prop);
        ITeacherRepo teacherRepo = new TeacherRepo(prop);
        ICourseRepo courseRepo = new CourseRepo(prop, teacherRepo);

        service = new Service(studentRepo, teacherRepo, courseRepo, studentValidator, teacherValidator, courseValidator);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginIn-view.fxml"));

        AnchorPane loginLayout = fxmlLoader.load();
        stage.setScene(new Scene(loginLayout));

        LogInController loginController = fxmlLoader.getController();
        loginController.setService(service);
        loginController.setDialogStage(stage);
        stage.setTitle("Welcome to creative corner! <3");
        stage.show();

    }
}
