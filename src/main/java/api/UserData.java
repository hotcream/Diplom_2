package api;

public class UserData {

    private String email;
    private String password;
    private String name;

    public UserData(String userName, String userMail, String userPassword) {
        this.name = userName;
        this.email = userMail;
        this.password = userPassword;
    }

    public UserData() {
        this.name = Base.createUserName();
        this.email = Base.createMail();
        this.password = Base.createPassword();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}