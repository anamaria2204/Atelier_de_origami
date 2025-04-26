package atelier.atelier_de_origami.controller;

import atelier.atelier_de_origami.domain.Course;
import atelier.atelier_de_origami.domain.CourseCategory;
import atelier.atelier_de_origami.domain.ExperienceLevel;
import atelier.atelier_de_origami.domain.Teacher;
import atelier.atelier_de_origami.service.Service;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TeacherController {
    Service service;
    Stage stage;
    Teacher teacher;
    ObservableList<Course> courseList = FXCollections.observableArrayList();

    @FXML
    Label welcomeLabel;
    @FXML
    ComboBox<String> ageComboBox;
    @FXML
    ComboBox<String> experienceComboBox;

    @FXML
    TextField courseNameField;
    @FXML
    TextField courseDateField;
    @FXML
    TextField courseDurationField;
    @FXML
    TextField coursePriceField;
    @FXML
    TextField courseMaxParticipantsField;

    @FXML
    TableView<Course> courseTableView;
    @FXML
    TableColumn<Course, String> courseNameColumn;
    @FXML
    TableColumn<Course, String> courseDateColumn;
    @FXML
    TableColumn<Course, String> courseDurationColumn;
    @FXML
    TableColumn<Course, String> coursePriceColumn;
    @FXML
    TableColumn<Course, String> courseCategoryColumn;
    @FXML
    TableColumn<Course, String> courseExperienceLevelColumn;
    @FXML
    TableColumn<Course, String> courseMaxParticipantsColumn;

    public void setService(Service service, Teacher teacher) {
        this.service = service;
        this.teacher = teacher;
        initModel();
    }
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        courseTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateCourseFields(newSelection);
            }
        });
    }

    private void initModel() {
        welcomeLabel.setText("Welcome, " + teacher.getFirstname() + " " + teacher.getLastname() + "!");

        for (CourseCategory category : CourseCategory.values()) {
            String displayValue;
                switch (category) {
                    case under_10_years -> displayValue = "< 10 years";
                    case between_10_and_18_years -> displayValue = "10-18 years";
                    case between_18_and_25_years -> displayValue = "18-25 years";
                    case over_25_years -> displayValue = "> 25 years";
                    default -> displayValue = category.toString();
                }
            ageComboBox.getItems().add(displayValue);
        }

        for (ExperienceLevel level : ExperienceLevel.values()) {
            experienceComboBox.getItems().add(level.toString());
        }

        if (courseNameColumn.getCellValueFactory() == null) {
            courseNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            courseDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
            courseDurationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDuration())));
            coursePriceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrice())));
            courseCategoryColumn.setCellValueFactory(cellData -> {
                CourseCategory category = cellData.getValue().getCategory();
                String displayValue;
                switch (category) {
                    case under_10_years -> displayValue = "< 10 years";
                    case between_10_and_18_years -> displayValue = "10-18 years";
                    case between_18_and_25_years -> displayValue = "18-25 years";
                    case over_25_years -> displayValue = "> 25 years";
                    default -> displayValue = category.toString();
                }
                return new SimpleStringProperty(displayValue);
            });
            courseExperienceLevelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExperienceLevel().toString()));
            courseMaxParticipantsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMaxParticipants())));
        }

        Iterable<Course> courses = service.getAllCoursesByTeacher(teacher.getId());
        courses.forEach(courseList::add);
        courseTableView.setItems(courseList);
    }

    private void populateCourseFields(Course course) {
        courseNameField.setText(course.getName());
        courseDateField.setText(course.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        courseDurationField.setText(String.valueOf(course.getDuration()));
        coursePriceField.setText(String.valueOf(course.getPrice()));
        courseMaxParticipantsField.setText(String.valueOf(course.getMaxParticipants()));
        CourseCategory category = course.getCategory();
        String categoryDisplay = switch (category) {
            case under_10_years -> "< 10 years";
            case between_10_and_18_years -> "10-18 years";
            case between_18_and_25_years -> "18-25 years";
            case over_25_years -> "> 25 years";
            default -> category.toString();
        };
        ageComboBox.getSelectionModel().select(categoryDisplay);

        experienceComboBox.getSelectionModel().select(course.getExperienceLevel().toString());
    }

    @FXML
    private void handleAddButton(){
        String name = courseNameField.getText();
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(courseDateField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        }
        catch (Exception e) {
            showAlert("Invalid date format", "Please enter the date in the format dd/MM/yyyy HH:mm", Alert.AlertType.ERROR);
            return;
        }
        int duration = Integer.parseInt(courseDurationField.getText());
        float price = Float.parseFloat(coursePriceField.getText());
        String selectedCategory = ageComboBox.getSelectionModel().getSelectedItem();
        CourseCategory category = switch (selectedCategory) {
            case "< 10 years" -> CourseCategory.under_10_years;
            case "10-18 years" -> CourseCategory.between_10_and_18_years;
            case "18-25 years" -> CourseCategory.between_18_and_25_years;
            case "> 25 years" -> CourseCategory.over_25_years;
            default -> throw new IllegalArgumentException("Unknown category: " + selectedCategory);
        };
        ExperienceLevel experienceLevel = ExperienceLevel.valueOf(experienceComboBox.getSelectionModel().getSelectedItem());
        int maxParticipants = Integer.parseInt(courseMaxParticipantsField.getText());
        if(name.isEmpty() || date == null || duration <= 0 || price <= 0 || maxParticipants <= 0 || selectedCategory == null || experienceLevel == null) {
            showAlert("Error", "Please fill in all fields correctly!", Alert.AlertType.ERROR);
            return;
        }

        Course course = new Course(name, date, duration, price, category, teacher, experienceLevel, maxParticipants);
        service.addCourse(course);
        courseList.add(course);
        courseTableView.refresh();

        clearFields();
        showAlert("Course added", "Course added successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleUpdateButton(){
        String name = courseNameField.getText();
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(courseDateField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        }
        catch (Exception e) {
            showAlert("Invalid date format", "Please enter the date in the format dd/MM/yyyy HH:mm", Alert.AlertType.ERROR);
            return;
        }
        int duration = Integer.parseInt(courseDurationField.getText());
        float price = Float.parseFloat(coursePriceField.getText());
        String selectedCategory = ageComboBox.getSelectionModel().getSelectedItem();
        CourseCategory category = switch (selectedCategory) {
            case "< 10 years" -> CourseCategory.under_10_years;
            case "10-18 years" -> CourseCategory.between_10_and_18_years;
            case "18-25 years" -> CourseCategory.between_18_and_25_years;
            case "> 25 years" -> CourseCategory.over_25_years;
            default -> throw new IllegalArgumentException("Unknown category: " + selectedCategory);
        };
        ExperienceLevel experienceLevel = ExperienceLevel.valueOf(experienceComboBox.getSelectionModel().getSelectedItem());
        int maxParticipants = Integer.parseInt(courseMaxParticipantsField.getText());
        if(name.isEmpty() || date == null || duration <= 0 || price <= 0 || maxParticipants <= 0 || selectedCategory == null || experienceLevel == null) {
            showAlert("Error", "Please fill in all fields correctly!", Alert.AlertType.ERROR);
            return;
        }

        Course course = courseTableView.getSelectionModel().getSelectedItem();
        service.updateCourse(course, name, date, duration, price, category, experienceLevel, maxParticipants);
        courseList.set(courseList.indexOf(course), course);
        courseTableView.refresh();
        clearFields();
        showAlert("Course updated", "Course updated successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleDeleteButton(){
        Course course = courseTableView.getSelectionModel().getSelectedItem();
        if (course != null) {
            service.deleteCourse(course.getId());
            courseList.remove(course);
            courseTableView.refresh();
            clearFields();
            showAlert("Course deleted", "Course deleted successfully!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Please select a course to delete!", Alert.AlertType.ERROR);
        }
    }

    private void clearFields(){
        courseNameField.clear();
        courseDateField.clear();
        courseDurationField.clear();
        coursePriceField.clear();
        courseMaxParticipantsField.clear();
        ageComboBox.getSelectionModel().clearSelection();
        experienceComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
