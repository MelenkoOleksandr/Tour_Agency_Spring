package com.example.test.dto;

import com.example.test.models.UserType;

public class UserRegisterCredentials {
    private String email;
    private String password;
    private String name;
    private UserType userType;

    public UserRegisterCredentials() {}

    public UserRegisterCredentials(String email, String password, String name, UserType userType) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public UserType getUserType() { return userType; }

    public void setUserType(UserType userType) { this.userType = userType; }
}
