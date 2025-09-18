package com.lorenzon.weather_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${WEATHER_API_KEY}")
    private String apiKey;

    public String getWeatherApi(String location) {
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                .concat(location).concat("?key=").concat(apiKey);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class).getBody();
    }
}
