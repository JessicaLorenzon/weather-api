package com.lorenzon.weather_api.exception;

public class TooManyRequestsException extends RuntimeException{

    public TooManyRequestsException() {
      super("The account has exceeded their assigned limits.");
    }
}
