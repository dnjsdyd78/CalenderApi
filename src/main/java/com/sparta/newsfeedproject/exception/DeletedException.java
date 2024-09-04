package com.sparta.newsfeedproject.exception;

import org.springframework.http.HttpStatus;

public class DeletedException extends RuntimeException {

    private final HttpStatus httpStatus;

    public DeletedException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
