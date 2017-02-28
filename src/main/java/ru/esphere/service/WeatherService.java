package ru.esphere.service;

import ru.esphere.model.Weather;

public interface WeatherService {

    Weather getWeather(String location);

    Weather getWeather(double latitude, double longitude);
}
