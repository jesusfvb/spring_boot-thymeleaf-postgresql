package com.backend.backend.controls.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptiosnHandler {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<Object> userException(UserException e) {
        return new ResponseEntity<>(e, e.getStatus());

    }

    @ExceptionHandler(value = UbicacionException.class)
    public ResponseEntity<Object> ubicacionException(UbicacionException e) {
        return new ResponseEntity<>(e, e.getStatus());

    }

    @ExceptionHandler(value = CuarteleriaException.class)
    public ResponseEntity<Object> ubicacionException(CuarteleriaException e) {
        return new ResponseEntity<>(e, e.getStatus());

    }

    @ExceptionHandler(value = GuardiaException.class)
    public ResponseEntity<Object> ubicacionException(GuardiaException e) {
        return new ResponseEntity<>(e, e.getStatus());

    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Exception pivote = new Exception("Objeto Recivido Incorrecto", e.getCause());
        return new ResponseEntity<>(pivote, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> NoSuchElementException(NoSuchElementException e) {
        Exception pivote = new RuntimeException("No se encontro en la Base de Datos");
        return new ResponseEntity<>(pivote, HttpStatus.NOT_FOUND);
    }

}
