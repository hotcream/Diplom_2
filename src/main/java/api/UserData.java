package api;

public class UserData {

    private String userName;
    private String userMail;
    private String userPassword;

    public UserData(String userName, String userMail, String userPassword) {
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;
    }

    public UserData() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}