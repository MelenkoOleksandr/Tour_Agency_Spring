package com.example.test.dto;

public class UserLoginCredentials {
    private String email;
    private String password;

    public UserLoginCredentials() {}

    public UserLoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
