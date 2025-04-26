package atelier.atelier_de_origami.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Course extends Entity<Integer> {
    private String name;
    private LocalDateTime date;
    private int duration;
    private float price;
    private CourseCategory category;
    private Teacher teacher;
    private ExperienceLevel experienceLevel;
    private int maxParticipants;

    public Course(int id, String name, LocalDateTime date, int duration, float price, CourseCategory category, Teacher teacher, ExperienceLevel experienceLevel, int maxParticipants) {
        setId(id);
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.price = price;
        this.category = category;
        this.teacher = teacher;
        this.experienceLevel = experienceLevel;
        this.maxParticipants = maxParticipants;
    }

    public Course(String name, LocalDateTime date, int duration, float price, CourseCategory category, Teacher teacher, ExperienceLevel experienceLevel, int maxParticipants) {
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.price = price;
        this.category = category;
        this.teacher = teacher;
        this.experienceLevel = experienceLevel;
        this.maxParticipants = maxParticipants;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public CourseCategory getCategory() {
        return category;
    }
    public void setCategory(CourseCategory category) {
        this.category = category;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public ExperienceLevel getExperienceLevel() {
        return experienceLevel;
    }
    public void setExperienceLevel(ExperienceLevel experienceLevel) {
        this.experienceLevel = experienceLevel;
    }
    public int getMaxParticipants() {
        return maxParticipants;
    }
    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

}
