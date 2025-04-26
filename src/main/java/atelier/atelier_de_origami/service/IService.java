package atelier.atelier_de_origami.service;

import atelier.atelier_de_origami.domain.*;

import java.time.LocalDateTime;

public interface IService {
    Iterable<Student> getAllStudents();
    Iterable<Teacher> getAllTeachers();
    Iterable<Course> getAllCourses();
    Iterable<Course> getAllCoursesByTeacher(int idTeacher);
    Teacher getTeacherByUsername(String username);
    Student getStudentByUsername(String username);
    Course addCourse(Course course);
    Course updateCourse(Course course, String name, LocalDateTime date, int duration, double price, CourseCategory category, ExperienceLevel experienceLevel, int maxParticipants);
    Course deleteCourse(int id);

}
