package atelier.atelier_de_origami.domain;

import atelier.atelier_de_origami.controller.TeacherController;

import java.time.LocalDateTime;

public class Booking extends Entity<Integer>{
    private Student student;
    private Teacher teacher;
    private Course course;
    private LocalDateTime date;

    public Booking(Student student, Teacher teacher, Course course, LocalDateTime date) {
        this.student = student;
        this.teacher = teacher;
        this.course = course;
        this.date = date;
    }

    public Booking(Integer id, Student student, Teacher teacher, Course course, LocalDateTime date) {
        setId(id);
        this.student = student;
        this.teacher = teacher;
        this.course = course;
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
