package ru.esphere.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.esphere.model.User;
import ru.esphere.model.UserStatistic;
import ru.esphere.repository.UserInfoRepository;
import ru.esphere.repository.helpers.UserStatisticMapper;
import ru.esphere.repository.helpers.UserInfoQuery;
import ru.esphere.repository.helpers.UserMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository {

    private JdbcTemplate template;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public UserInfoRepositoryImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUserByName(String userName) {
        try {
            return template.queryForObject(UserInfoQuery.SELECT_USER_BY_NAME, new UserMapper(), userName);
        } catch (EmptyResultDataAccessException e) {
            logger.info("User - \"" + userName + "\" not found");
            return null;
        }
    }

    @Override
    public void saveUser(User user) {
        template.update(UserInfoQuery.CREATE_USER, user.getName(), user.getPassword());
        logger.info("User - \"" + user.getName() + "\" created");
    }

    @Override
    public List<UserStatistic> getUserStatistic(Long userId) {
        return template.query(UserInfoQuery.SELECT_USER_STATISTIC, new UserStatisticMapper(), userId);
    }

    @Override
    public void saveUserStatistic(UserStatistic userStatistic) {
        template.update(UserInfoQuery.CREATE_USER_STATISTIC,
                userStatistic.getUserId(), userStatistic.getActionTime(),
                userStatistic.getAction(), userStatistic.getDescription());
    }
}
