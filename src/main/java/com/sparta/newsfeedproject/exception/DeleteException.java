package com.sparta.newsfeedproject.exception;

import org.springframework.http.HttpStatus;

public class DeleteException extends RuntimeException {

    private final HttpStatus httpStatus;

    public DeleteException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
