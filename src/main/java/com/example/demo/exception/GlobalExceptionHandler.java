// 1. Fängt alle Validierungsfehler ab, die durch Annotationen an Methodenparametern entstehen (z.B. PUT)
// 2. Gibt 500 Internal Server Error zurück.

package com.example.demo.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations() // gibt alle Validierungsfehler zurück (messages)
            .stream()
            .findFirst() // springt zum ersten Fehler
            .map(ConstraintViolation::getMessage) // gibt die Nachricht zurück
            .orElse("Validierungsfehler"); // wenn kein Fehler vorhanden ist, wird "Validierungsfehler" zurückgegeben.
   
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message); // 500 Internal Server Error
    }
}