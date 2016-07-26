package ru.esphere.service;

import ru.esphere.model.Weather;

public interface WeatherService {
    Weather getWeatherByLocationName(String location);
    Weather getWeatherByCoordinates(double latitude, double longitude);
}
