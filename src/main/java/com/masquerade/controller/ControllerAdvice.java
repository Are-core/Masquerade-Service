package com.masquerade.controller;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            EntityRequestException.class
    })
    public ResponseEntity<String> handleAnyException(Exception e, WebRequest request) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            BadRequestException.class
    })
    public ResponseEntity<String> handleBadRequestException(Exception e, WebRequest request) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
