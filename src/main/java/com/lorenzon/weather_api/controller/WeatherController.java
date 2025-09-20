package com.lorenzon.weather_api.controller;

import com.lorenzon.weather_api.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{location}")
    public ResponseEntity<String> getWeather(@PathVariable String location) throws Exception {
        String resultWeather = weatherService.getWeatherApi(location);
        return ResponseEntity.ok(resultWeather);
    }
}
