package atelier.atelier_de_origami.domain;

public class User<ID> {

    private ID id;
    private String username;
    private String password;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public User(ID id, String username, String password) {
        setId(id);
        this.username = username;
        this.password = password;
    }

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
