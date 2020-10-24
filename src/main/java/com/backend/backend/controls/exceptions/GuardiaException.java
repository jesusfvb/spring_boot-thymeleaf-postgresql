package com.backend.backend.controls.exceptions;


import org.springframework.http.HttpStatus;

public class GuardiaException extends RuntimeException {
    /**
     *
     */

    private HttpStatus status;

    public GuardiaException(String message) {
        super("GuardiaException: " + message);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public GuardiaException(String message, HttpStatus status) {
        super("GuardiaException: " + message);
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
