package com.lorenzon.weather_api.exceptionhandler;

import com.lorenzon.weather_api.exception.BadRequestException;
import com.lorenzon.weather_api.exception.NotFoundException;
import com.lorenzon.weather_api.exception.TooManyRequestsException;
import com.lorenzon.weather_api.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequest(BadRequestException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Invalid parameter");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://weather-api.com/errors/bad-request"));

        return problemDetail;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ProblemDetail handleUnauthorized(UnauthorizedException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle("Unauthorized");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://weather-api.com/errors/unauthorized"));

        return problemDetail;
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFound(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Not found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://weather-api.com/errors/not-found"));

        return problemDetail;
    }

    @ExceptionHandler(TooManyRequestsException.class)
    public ProblemDetail handleTooManyRequests(TooManyRequestsException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.TOO_MANY_REQUESTS);
        problemDetail.setTitle("Too many requests");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("https://weather-api.com/errors/too-many-requests"));

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Unexpected error");
        problemDetail.setDetail("An internal error occurred. Please try again.");
        problemDetail.setType(URI.create("https://weather-api.com/errors/internal"));

        return problemDetail;
    }
}
