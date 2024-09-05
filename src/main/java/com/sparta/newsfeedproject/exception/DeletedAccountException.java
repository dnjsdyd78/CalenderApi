package com.sparta.newsfeedproject.exception;

import org.springframework.http.HttpStatus;

public class DeletedAccountException extends RuntimeException {

    private final HttpStatus httpStatus;

    public DeletedAccountException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
