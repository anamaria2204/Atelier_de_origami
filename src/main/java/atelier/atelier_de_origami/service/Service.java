package atelier.atelier_de_origami.service;

import atelier.atelier_de_origami.domain.*;
import atelier.atelier_de_origami.domain.validator.CourseValidator;
import atelier.atelier_de_origami.domain.validator.StudentValidator;
import atelier.atelier_de_origami.domain.validator.TeacherValidator;
import atelier.atelier_de_origami.repository.ICourseRepo;
import atelier.atelier_de_origami.repository.IStudentRepo;
import atelier.atelier_de_origami.repository.ITeacherRepo;

import java.time.LocalDateTime;

public class Service implements IService {

    private final IStudentRepo studentRepo;
    private final ITeacherRepo teacherRepo;
    private final ICourseRepo courseRepo;
    private final StudentValidator studentValidator;
    private final TeacherValidator teacherValidator;
    private final CourseValidator courseValidator;

    public Service(IStudentRepo studentRepo, ITeacherRepo teacherRepo, ICourseRepo courseRepo, StudentValidator studentValidator, TeacherValidator teacherValidator, CourseValidator courseValidator) {
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
        this.courseRepo = courseRepo;
        this.studentValidator = studentValidator;
        this.teacherValidator = teacherValidator;
        this.courseValidator = courseValidator;
    }
    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Iterable<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    @Override
    public Iterable<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Iterable<Course> getAllCoursesByTeacher(int idTeacher) {
        return courseRepo.findAllCourseTeacher(idTeacher);
    }

    @Override
    public Teacher getTeacherByUsername(String username) {
        return teacherRepo.findByUsername(username);
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentRepo.findByUsername(username);
    }

    @Override
    public Course addCourse(Course course) {
        try{
            courseValidator.validate(course);
            String name = course.getName();
            LocalDateTime date = course.getDate();
            int duration = course.getDuration();
            float price = course.getPrice();
            CourseCategory category = course.getCategory();
            Teacher teacher = course.getTeacher();
            ExperienceLevel experienceLevel = course.getExperienceLevel();
            int maxParticipants = course.getMaxParticipants();
            if (teacher == null) {
                throw new IllegalArgumentException("Teacher cannot be null");
            }
            courseRepo.save(name, date, duration, price, category, teacher, experienceLevel, maxParticipants);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return course;
    }

    @Override
    public Course updateCourse(Course course, String name, LocalDateTime date, int duration, double price, CourseCategory category, ExperienceLevel experienceLevel, int maxParticipants) {
        try {
            courseValidator.validate(course);
            course.setName(name);
            course.setDate(date);
            course.setDuration(duration);
            float price_f = (float) price;
            course.setPrice(price_f);
            course.setCategory(category);
            course.setExperienceLevel(experienceLevel);
            course.setMaxParticipants(maxParticipants);
            courseRepo.update(course, name, date, duration, price_f, category, experienceLevel, maxParticipants);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public Course deleteCourse(int id) {
        try{
            Course course = courseRepo.delete(id);
            if (course == null) {
                throw new IllegalArgumentException("Course not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
