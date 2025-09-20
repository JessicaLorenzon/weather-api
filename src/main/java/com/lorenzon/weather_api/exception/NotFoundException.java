package com.lorenzon.weather_api.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("The request cannot be matched to any valid API request endpoint structure.");
    }
}
