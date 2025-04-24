package atelier.atelier_de_origami.domain;

public class Student extends User<Integer>{

    private String firstname;
    private String lastname;
    private int age;
    private String phoneNumber;
    private String email;

    public Student(int id, String firstname, String lastname, int age, String phoneNumber, String email, String username, String password) {
        super(id, username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Student(String firstname, String lastname, int age, String phoneNumber, String email, String username, String password) {
        super(username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
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
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
