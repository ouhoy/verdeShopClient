package com.dahmou.verdeShop.repository;

import com.dahmou.verdeShop.model.User;
import com.dahmou.verdeShop.model.UserType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository

public class UserCollectionRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserCollectionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("email"),
                UserType.valueOf(rs.getString("userType"))
        );
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";

        return jdbcTemplate.query(sql, UserCollectionRepository::mapRow);

    }


    public void createUser(User user) {

        if (isEmailExists(user.email())) {
            throw new RuntimeException("Email already exists. Choose another email.");
        }


        String sql = "INSERT INTO Users (firstname, lastname,email, userType) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, user.firstName(), user.lastName(), user.email(), user.userType().toString());

    }

    private boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM Users WHERE email=?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
        return count > 0;
    }

    public void updateUser(int id, User user) {
        String sql = "UPDATE Users SET firstname=?, lastname=?,email=?, userType=? WHERE id=?";
        jdbcTemplate.update(sql, user.firstName(), user.lastName(), user.email(), user.userType().toString(), id);

    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM Users WHERE id=?";
        jdbcTemplate.update(sql, id);

    }

    public User getUser(int id) {
        try {
            String sql = "SELECT * FROM Users WHERE id=?";

            return jdbcTemplate.queryForObject(sql, new Object[]{id}, UserCollectionRepository::mapRow);
        } catch (EmptyResultDataAccessException e) {
            // Handle empty result - throw a custom exception or return null
            return null; // Or throw a custom exception
        }
    }

    public User getUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM Users WHERE email=?";

            return jdbcTemplate.queryForObject(sql, new Object[]{email}, UserCollectionRepository::mapRow);
        } catch (EmptyResultDataAccessException e) {
            // Handle empty result - throw a custom exception or return null
            return null; // Or throw a custom exception
        }
    }

}




