package atelier.atelier_de_origami.domain.validator;

import atelier.atelier_de_origami.domain.Booking;

import java.time.LocalDateTime;

public class BookingValidator implements Validator {
    @Override
    public void validate(Object entity) throws Exception {
        if(entity == null) {
            throw new Exception("Booking cannot be null");
        }
        if(!(entity instanceof Booking)) {
            throw new Exception("Invalid entity type");
        }
        Booking booking = (Booking) entity;
        if(booking.getStudent() == null) {
            throw new Exception("Student cannot be null");
        }
        if(booking.getTeacher() == null) {
            throw new Exception("Teacher cannot be null");
        }
        if(booking.getCourse() == null) {
            throw new Exception("Course cannot be null");
        }
        if(booking.getDate() == null) {
            throw new Exception("Date cannot be null");
        }
        if(booking.getDate().isBefore(LocalDateTime.now())) {
            throw new Exception("Date cannot be in the past");
        }
        if(booking.getDate().getMinute() % 15 != 0) {
            throw new Exception("Date must be on a 15 minute interval");
        }
        if(booking.getDate().getHour() < 8 || booking.getDate().getHour() > 20) {
            throw new Exception("Date must be between 8:00 and 20:00");
        }

    }
}
