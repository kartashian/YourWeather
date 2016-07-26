package ru.esphere.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.esphere.config.WebConfig;
import ru.esphere.model.User;
import ru.esphere.model.UserStatistic;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@Transactional
public class UserInfoRepositoryTest {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void getUserTest() {
        User user = userInfoRepository.getUserByName("user");
        assertNotNull(user);
    }

    @Test
    public void saveUserTest() {
        User user = new User("admin", "123");
        userInfoRepository.saveUser(user);
        User checkUser = userInfoRepository.getUserByName(user.getName());
        assertEquals(user.getName(), checkUser.getName());
    }

    @Test
    public void getUserStatisticTest() {
        User user = userInfoRepository.getUserByName("user");
        List<UserStatistic> userStatistic = userInfoRepository.getUserStatistic(user.getId());
        assertFalse(userStatistic.isEmpty());
    }

    @Test
    public void saveUserStatisticTest() {
        userInfoRepository.saveUser(new User("statUser", "123"));
        User user = userInfoRepository.getUserByName("statUser");
        UserStatistic userStatistic = new UserStatistic(user.getId(), new Date(), "LOGIN", "");
        userInfoRepository.saveUserStatistic(userStatistic);
        List<UserStatistic> statistics = userInfoRepository.getUserStatistic(user.getId());
        assertFalse(statistics.isEmpty());
    }
}
