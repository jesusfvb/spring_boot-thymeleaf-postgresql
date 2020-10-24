package com.backend.backend.controls.exceptions;


import org.springframework.http.HttpStatus;

public class NotificacionException extends RuntimeException {
    /**
     *
     */

    private HttpStatus status;

    public NotificacionException(String message) {
        super("NotificacionException: " + message);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public NotificacionException(String message, HttpStatus status) {
        super("NotificacionException: " + message);
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
