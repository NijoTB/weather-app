package com.example.weatherhackathon;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherFetch {
    private static final String API_KEY = System.getenv("OPENWEATHER_API_KEY");
    private static final String WEATHER_API_BASE_URL = "https://api.openweathermap.org/data/3.0/onecall";
    private static final String LOCATION_API_BASE_URL = "https://api.openweathermap.org/geo/1.0/direct";

    public void fetchWeather(double lat, double lon) {

    }

    public static JSONObject fetchJSON(URL url) throws IOException {



        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");


        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {

                response.append(line);

            }

            reader.close();


            String jsonResponse = response.toString().replaceAll("^\\[|]$", "");

            return new JSONObject(jsonResponse);

        } else {

            throw new IOException("Error fetching data. Response code: " + responseCode);

        }

    }

    public static JSONObject covertCityStateToLatLon(String city, String state) throws IOException{
        String apiUrl = String.format("%s?q=%s,%s,US&limit=1&appid=%s",
                LOCATION_API_BASE_URL,
                city,
                state,
                API_KEY);

        URL url = new URL(apiUrl);

        return fetchJSON(url);
    }

    public static JSONObject fetchWeatherForApp(double latitude, double longitude) throws IOException {
        String apiUrl = String.format("%s?lat=%.2f&lon=%.2f&units=imperial&appid=%s",
                WEATHER_API_BASE_URL,
                latitude,
                longitude,
                API_KEY);

        URL url = new URL(apiUrl);

        return fetchJSON(url);


    }

    public static JSONObject fetchWeatherByCityState(String city, String state) throws IOException {
        JSONObject json = covertCityStateToLatLon(city, state);
        double latitude = json.getDouble("lat");
        double longitude = json.getDouble("lon");
       return fetchWeatherForApp(latitude, longitude);
    }
}
