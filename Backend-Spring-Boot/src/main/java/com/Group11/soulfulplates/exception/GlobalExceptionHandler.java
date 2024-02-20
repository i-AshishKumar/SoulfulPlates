package com.Group11.soulfulplates.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("code", -1);
        errorDetails.put("description", "Invalid input format. Expected a numeric value.");
        errorDetails.put("data", null);

        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("description", "Invalid request: " + ex.getMessage());
        response.put("data", null); // Adjust based on your error response structure
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
