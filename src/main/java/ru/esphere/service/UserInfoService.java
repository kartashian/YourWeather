package ru.esphere.service;

import ru.esphere.model.User;
import ru.esphere.model.UserStatistic;
import ru.esphere.service.exception.UserNameDuplicatedException;

import java.util.List;

public interface UserInfoService {
    User getCurrentUser();
    void createUser(User user) throws UserNameDuplicatedException;
    List<UserStatistic> getUserStatistic(Long userId);
}
