package ru.esphere.model;

public class Weather {
    private int temperature;
    private int windSpeed;
    private int pressure;
    private int humidity;

    public Weather(int temperature, int windSpeed, int pressure, int humidity) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Temperature: " + temperature + " C , Wind Speed: " + windSpeed + " m/s," +
                " Pressure: " + pressure + " mm Hg, Humidity: " + humidity + " %";
    }
}
