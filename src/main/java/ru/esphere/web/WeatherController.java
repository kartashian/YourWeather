package ru.esphere.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.esphere.model.Weather;
import ru.esphere.service.WeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    @ResponseBody
    public Weather getWeatherByLocation(@RequestParam("location") String location) {
        return weatherService.getWeather(location);
    }

    @RequestMapping(value = "/coordinate", method = RequestMethod.GET)
    @ResponseBody
    public Weather getWeatherByCoordinate(@RequestParam("latitude") double latitude,
                                          @RequestParam("longitude") double longitude) {
        return weatherService.getWeather(latitude, longitude);
    }
}
