package com.example.test.dao.impl;

import com.example.test.dao.interfaces.UserDao;
import com.example.test.dto.UserRegisterCredentials;
import com.example.test.models.User;
import com.example.test.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User register(UserRegisterCredentials credentials) {
        String sql = "INSERT INTO users (email, password, name, user_type) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, credentials.getEmail(), credentials.getPassword(), credentials.getName(), credentials.getUserType().toString());
        return getUserByEmail(credentials.getEmail());
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, this::mapUserRow);
    }

    @Override
    public User getUserById(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(sql, this::mapUserRow, id).get(0);
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET email = ?, img = ?, name = ?, surname = ?, phone = ? WHERE id = ?;";
        jdbcTemplate.update(sql, user.getEmail(), user.getImg(), user.getName(), user.getSurname(), user.getPhone(), user.getId());
    }

    @Override
    public void deleteUser(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.query(sql, this::mapUserRow, email).get(0);
    }

    private User mapUserRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setUserType(UserType.valueOf(rs.getString("user_type")));
        return user;
    }
}
