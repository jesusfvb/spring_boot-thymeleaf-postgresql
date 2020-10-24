package com.backend.backend.controls.exceptions;


import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {
    /**
     *
     */

    private HttpStatus status;

    public UserException(String message) {
        super("UserException: " + message);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public UserException(String message, HttpStatus status) {
        super("UserException: " + message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    private static final long serialVersionUID = 1L;
}
