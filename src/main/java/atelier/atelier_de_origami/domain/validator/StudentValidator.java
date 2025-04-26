package atelier.atelier_de_origami.domain.validator;

public class StudentValidator implements Validator{
    @Override
    public void validate(Object entity) throws Exception {
        if(entity instanceof atelier.atelier_de_origami.domain.Student) {
            atelier.atelier_de_origami.domain.Student student = (atelier.atelier_de_origami.domain.Student) entity;
            if (student.getUsername() == null || student.getUsername().equals("")) {
                throw new Exception("The username cannot be empty!");
            }
            if (student.getPassword() == null || student.getPassword().equals("")) {
                throw new Exception("The password cannot be empty!");
            }
            if (student.getFirstname() == null || student.getFirstname().equals("")) {
                throw new Exception("The first name cannot be empty!");
            }
            if (student.getLastname() == null || student.getLastname().equals("")) {
                throw new Exception("The last name cannot be empty!");
            }
            if (student.getEmail() == null || student.getEmail().equals("")) {
                throw new Exception("The email cannot be empty!");
            }
            if (student.getPhoneNumber() == null || student.getPhoneNumber().equals("")) {
                throw new Exception("The phone number cannot be empty!");
            }
        } else {
            throw new Exception("The entity is not an instance of Student!");
        }
    }
}
