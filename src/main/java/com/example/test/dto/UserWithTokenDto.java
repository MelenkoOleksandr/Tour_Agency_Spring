package com.example.test.dto;

import com.example.test.models.User;

public class UserWithTokenDto {
    private User user;
    private String refreshToken;

    public UserWithTokenDto(User user, String refreshToken) {
        this.user = user;
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
