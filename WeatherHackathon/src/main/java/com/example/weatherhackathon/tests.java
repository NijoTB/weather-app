package com.example.weatherhackathon;

import org.json.JSONObject;

import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class tests {
    @Test
    public void TestFetchWeather() {
        try {

            //NYC
            double latitude = 40.7228;
            double longitude = 74.0060;

            JSONObject weatherData = WeatherFetch.fetchWeatherForApp(latitude, longitude);

            assertNotNull(weatherData);
            assertEquals(weatherData.getDouble("lat"), latitude, 0.1);
            assertEquals(weatherData.getDouble("lon"), longitude, 0.1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void weatherStaysInUsa(){

    }
}
