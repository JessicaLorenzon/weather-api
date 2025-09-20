package com.lorenzon.weather_api.exception;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException() {
        super("There is a problem with the API key, account or subscription.");
    }
}
