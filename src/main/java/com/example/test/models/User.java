package com.example.test.models;

public class User {
    private Long id;
    private String email;
    private String password;
    private String img;
    private String name;
    private String surname;
    private String phone;
    private UserType userType;

    public User(String email, String password, UserType userType, String name) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.name = name;
    }

    public User(long userId, String email, String img, String name, String surname, String phone) {
        this.id = userId;
        this.email = email;
        this.img = img;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
