package ru.esphere.repository.helpers;

import org.springframework.jdbc.core.RowMapper;
import ru.esphere.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("password"));
    }
}
