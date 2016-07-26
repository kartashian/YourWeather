package ru.esphere.repository.helpers;

import org.springframework.jdbc.core.RowMapper;
import ru.esphere.model.UserStatistic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserStatisticMapper implements RowMapper<UserStatistic> {
    @Override
    public UserStatistic mapRow(ResultSet resultSet, int i) throws SQLException {
        return new UserStatistic(resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                resultSet.getTimestamp("action_time"),
                resultSet.getString("action"),
                resultSet.getString("description")
        );
    }
}
