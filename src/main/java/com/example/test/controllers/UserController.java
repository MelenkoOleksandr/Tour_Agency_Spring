package com.example.test.controllers;

import com.example.test.models.User;
import com.example.test.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(HttpServletRequest request,  @PathVariable("id") long id, @RequestBody User user) {
        long authUserId = (int) request.getAttribute("userId");
        String userType = request.getAttribute("userType").toString();
        if (authUserId != id && !userType.equals("ADMIN")) {
            return ResponseEntity.badRequest().body("You can only update your own user");
        }

        userService.updateUser(id, user);
        return ResponseEntity.ok().body("Update user with id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(HttpServletRequest request, @PathVariable("id") long id) {
        long authUserId = (int) request.getAttribute("userId");
        String userType = request.getAttribute("userType").toString();
        if (authUserId != id && !userType.equals("ADMIN")) {
            return ResponseEntity.badRequest().body("You can only update your own user");
        }

        userService.deleteUser(id);
        return ResponseEntity.ok().body("Delete user with id " + id);
    }
}
