package atelier.atelier_de_origami.domain;

public class Teacher extends User<Integer> {

    private String firstname;
    private String lastname;
    private int age;


    public Teacher(int id, String firstname, String lastname, int age, String username, String password) {
        super(id, username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public Teacher(String firstname, String lastname, int age, String username, String password) {
        super(username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public int getAge() {
        return age;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
