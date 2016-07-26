package ru.esphere.repository.helpers;

public class UserInfoQuery {
    public static final String SELECT_USER_BY_NAME = "SELECT * FROM user WHERE name = ?";
    public static final String CREATE_USER = "INSERT INTO user(name, password) values(?,?)";
    public static final String SELECT_USER_STATISTIC = "SELECT * FROM user_statistic WHERE user_id = ?";
    public static final String CREATE_USER_STATISTIC = "INSERT INTO user_statistic(" +
            "user_id, " +
            "action_time, " +
            "action, " +
            "description) values(?,?,?,?)";
}
