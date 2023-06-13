package com.example.test.services;

import com.example.test.dao.interfaces.UserDao;
import com.example.test.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(long id)  {
        return userDao.getUserById(id);
    }

    public void updateUser(long id, User user)  {
        userDao.updateUser(user);
    }

    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }
}
