package com.mycompany.weatherapp;

import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class WeatherApp {
    private String city;
    private String temperature;
    private String condition;

   
    public WeatherApp(String city) {
        this.city = city;
        fetchWeather();
    }

    private void fetchWeather() {
        try {
            String apiKey = System.getenv("WEATHER_API_KEY"); 
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" 
                                + city + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            double temp = json.getJSONObject("main").getDouble("temp");
            String weatherDesc = json.getJSONArray("weather").getJSONObject(0).getString("description");

           
            this.temperature = String.valueOf(temp);
            this.condition = weatherDesc;

        } catch (Exception e) {
            this.temperature = "N/A";
            this.condition = "Error fetching weather";
        }
    }

    // Getter methods for GUI
    public String getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }
}

