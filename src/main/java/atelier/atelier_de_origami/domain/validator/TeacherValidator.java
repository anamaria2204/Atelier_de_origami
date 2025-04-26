package atelier.atelier_de_origami.domain.validator;

public class TeacherValidator implements Validator{
    @Override
    public void validate(Object entity) throws Exception {
        if(entity instanceof atelier.atelier_de_origami.domain.Teacher) {
            atelier.atelier_de_origami.domain.Teacher teacher = (atelier.atelier_de_origami.domain.Teacher) entity;
            if (teacher.getUsername() == null || teacher.getUsername().equals("")) {
                throw new Exception("The username cannot be empty!");
            }
            if (teacher.getPassword() == null || teacher.getPassword().equals("")) {
                throw new Exception("The password cannot be empty!");
            }
            if (teacher.getFirstname() == null || teacher.getFirstname().equals("")) {
                throw new Exception("The first name cannot be empty!");
            }
            if (teacher.getLastname() == null || teacher.getLastname().equals("")) {
                throw new Exception("The last name cannot be empty!");
            }
            if (teacher.getAge() <= 0 || teacher.getAge() >= 100) {
                throw new Exception("The age must be between 0 and 100!");
            }
        } else {
            throw new Exception("The entity is not an instance of Teacher!");
        }
    }
}
