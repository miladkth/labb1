package ui.DTOs;

import bo.entities.User;

public class UserDTO {
    private String userName;
    private String id;
    private String role;
    private String email;
    private boolean isActive;

    public UserDTO(User user) {
        if(user == null){
            return;
        }
        this.userName = user.getUserName();
        this.id = user.getId();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.isActive = user.getIsActive();
    }

    public boolean getIsActive() {
        return this.isActive;
    }
    public void setIsActive(boolean isActive){
        this.isActive = isActive;
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
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
