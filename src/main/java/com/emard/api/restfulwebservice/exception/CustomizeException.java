package com.emard.api.restfulwebservice.exception;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizeException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, WebRequest request) {
        ExceptionResponse re = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(re, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UserNotFoudException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoudException ex, WebRequest request) {
        ExceptionResponse re = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(re, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
                ExceptionResponse re = new ExceptionResponse(LocalDate.now(), "Validation Failed", ex.getBindingResult().getAllErrors().toString());
                return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
    }
    
}
