package atelier.atelier_de_origami.domain.validator;

import atelier.atelier_de_origami.domain.Course;

import java.time.LocalDateTime;

public class CourseValidator implements Validator {
    @Override
    public void validate(Object entity) throws Exception {
        if(entity == null) {
            throw new Exception("Course cannot be null");
        }
        if(!(entity instanceof Course)) {
            throw new Exception("Invalid entity type");
        }
        Course course = (Course) entity;
        if(course.getName() == null || course.getName().isEmpty()) {
            throw new Exception("Course title cannot be null or empty");
        }
        if (course.getDuration() <= 0) {
            throw new Exception("Course duration must be greater than 0");
        }
        if(course.getDate() == null) {
            throw new Exception("Course date cannot be null");
        }
        if(course.getDate().isBefore(LocalDateTime.now())) {
            throw new Exception("Date cannot be in the past");
        }
        if(course.getPrice() <= 0) {
            throw new Exception("Course price must be greater than 0");
        }
        if(course.getMaxParticipants() <= 0) {
            throw new Exception("Course max participants must be greater than 0");
        }
        if(course.getCategory() == null) {
            throw new Exception("Course category cannot be null");
        }
        if(course.getExperienceLevel() == null) {
            throw new Exception("Course experience level cannot be null");
        }
        TeacherValidator teacherValidator = new TeacherValidator();

        try {
            teacherValidator.validate(course.getTeacher());
        } catch (Exception e) {
            throw new Exception("Invalid teacher: " + e.getMessage());
        }
    }
}
