package com.sparta.newsfeedproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class JwtValidationException extends RuntimeException {

    private final HttpStatus httpStatus;

    public JwtValidationException(String Message) {
        super(Message);
        this.httpStatus = HttpStatus.UNAUTHORIZED;
    }
}
