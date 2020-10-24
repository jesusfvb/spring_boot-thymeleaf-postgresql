package com.backend.backend.controls.exceptions;


import org.springframework.http.HttpStatus;

public class CuarteleriaException extends RuntimeException {
    /**
     *
     */

    private HttpStatus status;

    public CuarteleriaException(String message) {
        super("CuarteleriaException: " + message);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CuarteleriaException(String message, HttpStatus status) {
        super("CuarteleriaException: " + message);
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
