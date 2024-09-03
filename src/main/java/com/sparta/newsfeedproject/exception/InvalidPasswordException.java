package com.sparta.newsfeedproject.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends RuntimeException {

    private final HttpStatus status;

    public InvalidPasswordException(HttpStatus httpStatus, String errorMessage) {
        super(errorMessage);
        this.status = httpStatus;
    }
}
