package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class MovieAPIException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;


    public MovieAPIException(String message, HttpStatus status) {
        super();
        this.message = message;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
