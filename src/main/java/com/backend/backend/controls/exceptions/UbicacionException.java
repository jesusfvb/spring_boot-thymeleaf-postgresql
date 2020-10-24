package com.backend.backend.controls.exceptions;


import org.springframework.http.HttpStatus;

public class UbicacionException extends RuntimeException {
    /**
     *
     */

    private HttpStatus status;

    public UbicacionException(String message) {
        super("UbicacionException: " + message);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public UbicacionException(String message, HttpStatus status) {
        super("UbicacionException: " + message);
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
