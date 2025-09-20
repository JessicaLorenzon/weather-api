package com.lorenzon.weather_api.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("The format of the API is incorrect or an invalid parameter or combination of parameters was supplied.");
    }
}
