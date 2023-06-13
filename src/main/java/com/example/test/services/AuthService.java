package com.example.test.services;

import com.example.test.dao.interfaces.UserDao;
import com.example.test.dto.UserLoginCredentials;
import com.example.test.dto.UserRegisterCredentials;
import com.example.test.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserDao userDao;
    private static final int WORKLOAD = 12;

    @Autowired
    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(UserLoginCredentials credentials) throws IllegalArgumentException{
        User user = userDao.getUserByEmail(credentials.getEmail());
        if (user == null || !checkPassword(credentials.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return user;
    }

    public User register(UserRegisterCredentials credentials) throws IllegalArgumentException {
        credentials.setPassword(hashPassword(credentials.getPassword()));
        return userDao.register(credentials);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hash provided for comparison");
        }
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(password, salt);
    }
}
