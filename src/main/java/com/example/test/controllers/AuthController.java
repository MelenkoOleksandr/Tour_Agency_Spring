package com.example.test.controllers;

import com.example.test.dto.UserLoginCredentials;
import com.example.test.dto.UserRegisterCredentials;
import com.example.test.dto.UserWithTokenDto;
import com.example.test.models.User;
import com.example.test.services.AuthService;
import com.example.test.services.UserService;
import com.example.test.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserWithTokenDto> login(@RequestBody UserLoginCredentials credentials) {
        User user = authService.login(credentials);
        return createAuthResponse(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserWithTokenDto> register(@RequestBody UserRegisterCredentials credentials) {
            User user = authService.register(credentials);
            return createAuthResponse(user);
    }

    private ResponseEntity<UserWithTokenDto> createAuthResponse( User user) {
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        String accessToken = JwtUtils.createAccessToken(user.getId().toString(), user.getUserType().toString(), "SECRET_KEY", 15);
        String refreshToken = JwtUtils.createRefreshToken(user.getId().toString(), user.getUserType().toString(), "SECRET_KEY", 30);

        ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
                .maxAge(60 * 60 * 24 * 30)
                .path("/")
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new UserWithTokenDto(user, refreshToken));
    }
}
