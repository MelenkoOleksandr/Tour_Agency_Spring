package com.example.test.dao.interfaces;

import com.example.test.dto.UserRegisterCredentials;
import com.example.test.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public User register(UserRegisterCredentials credentials);
    public List<User> getAllUsers();
    User getUserById(long id);
    void updateUser(User user);
    void deleteUser(long id);
    User getUserByEmail(String email);
}
