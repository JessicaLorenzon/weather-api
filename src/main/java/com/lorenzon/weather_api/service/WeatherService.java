package com.lorenzon.weather_api.service;

import com.lorenzon.weather_api.exception.BadRequestException;
import com.lorenzon.weather_api.exception.NotFoundException;
import com.lorenzon.weather_api.exception.TooManyRequestsException;
import com.lorenzon.weather_api.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpStatus.*;

@Service
public class WeatherService {

    @Value("${WEATHER_API_KEY}")
    private String apiKey;

    public String getWeatherApi(String location) throws Exception {
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                .concat(location).concat("?key=").concat(apiKey);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw switch (e.getStatusCode()) {
                case BAD_REQUEST -> new BadRequestException();
                case UNAUTHORIZED -> new UnauthorizedException();
                case NOT_FOUND -> new NotFoundException();
                default -> new TooManyRequestsException();
            };
        } catch (HttpServerErrorException e) {
            throw new Exception(e);
        }
    }
}
