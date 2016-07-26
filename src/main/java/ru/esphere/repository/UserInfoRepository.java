package ru.esphere.repository;

import ru.esphere.model.User;
import ru.esphere.model.UserStatistic;

import java.util.List;

public interface UserInfoRepository {
    User getUserByName(String userName);
    void saveUser(User user);
    List<UserStatistic> getUserStatistic(Long userId);
    void saveUserStatistic(UserStatistic userStatistic);
}
