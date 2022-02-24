package com.emard.api.restfulwebservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoudException extends RuntimeException {

    public UserNotFoudException(String message) {
        super(message);
    }

}
