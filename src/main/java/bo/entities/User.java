package bo.entities;

import java.util.UUID;

public class User {
    private String userName;
    private String id;
    private String password;
    private String role;
    private String email;

    public User(String id ,String userName, String password, String role, String email) {
        this.userName = userName;
        this.id = id;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public User(String userName, String password, String role, String email) {
        this.userName = userName;
        this.id = UUID.randomUUID().toString();
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.id = UUID.randomUUID().toString();
        this.password = password;
        this.role = "customer";
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
