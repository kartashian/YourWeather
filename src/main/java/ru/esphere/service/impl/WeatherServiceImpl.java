package ru.esphere.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.esphere.model.User;
import ru.esphere.model.UserAction;
import ru.esphere.model.UserStatistic;
import ru.esphere.model.Weather;
import ru.esphere.repository.UserInfoRepository;
import ru.esphere.service.UserInfoService;
import ru.esphere.service.WeatherService;

import java.util.Date;
import java.util.Random;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final UserInfoRepository userInfoRepository;

    private final UserInfoService userInfoService;

    @Autowired
    public WeatherServiceImpl(UserInfoRepository userInfoRepository, UserInfoService userInfoService) {
        this.userInfoRepository = userInfoRepository;
        this.userInfoService = userInfoService;
    }

    @Override
    public Weather getWeather(String location) {
        return generateWeather(location);
    }

    @Override
    public Weather getWeather(double latitude, double longitude) {
        String searchSource = String.valueOf(latitude) + " / " + String.valueOf(longitude);
        return generateWeather(searchSource);
    }

    private synchronized Weather generateWeather(String searchSource) {
        Weather weather = generateCurrentWeather();
        writeUserSearchStatistic(searchSource, weather);
        return weather;
    }

    @Transactional
    private void writeUserSearchStatistic(String searchSource, Weather weather) {
        User user = userInfoService.getCurrentUser();
        String description = String.format("Search - \"%s\". Result Weather - %s", searchSource, weather != null ? weather.toString() : "Not Found");
        UserStatistic statistic = new UserStatistic(user.getId(), new Date(),
                UserAction.SEARCH.name(), description);
        userInfoRepository.saveUserStatistic(statistic);
    }

    private Weather generateCurrentWeather() {
        int temperature = getRandomNumberInRange(15, 30);
        int windSpeed = getRandomNumberInRange(1, 10);
        int pressure = getRandomNumberInRange(740, 790);
        int humidity = getRandomNumberInRange(35, 85);
        return new Weather(temperature, windSpeed, pressure, humidity);
    }

    private int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }
}
