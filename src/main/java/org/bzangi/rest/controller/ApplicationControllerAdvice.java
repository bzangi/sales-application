package org.bzangi.rest.controller;

import org.bzangi.exception.CustomException;
import org.bzangi.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleCustomException(CustomException exception){
        String message = exception.getMessage();
        return new ApiErrors(message);
    }
}
