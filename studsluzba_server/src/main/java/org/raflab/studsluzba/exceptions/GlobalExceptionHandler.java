package org.raflab.studsluzba.exceptions;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 404 resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("errors", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // 409 resource already exists
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceAlreadyExistsException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("errors", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    // validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .collect(Collectors.toMap(
                    FieldError::getField,
                    DefaultMessageSourceResolvable::getDefaultMessage
            ));

        ex.getBindingResult().getGlobalErrors().forEach(error -> {
            fieldErrors.put(error.getObjectName(), error.getDefaultMessage());
        });

        Map<String, Object> body = new HashMap<>();
        body.put("errors", fieldErrors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // missing required fields, invalid JSON format
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Map<String, Object> body = new HashMap<>();

        String errorMessage = "Invalid JSON format or missing required fields";

        Throwable cause = ex.getCause();
        if (cause instanceof ValueInstantiationException) {
            ValueInstantiationException vie = (ValueInstantiationException) cause;
            String problemMessage = vie.getOriginalMessage();

            if (problemMessage != null && problemMessage.contains("is marked non-null but is null")) {
                String fieldName = problemMessage.substring(problemMessage.indexOf("problem: ") + 9,
                        problemMessage.indexOf(" is marked"));
                errorMessage = String.format("Field '%s' is required and cannot be null", fieldName);
            } else if (problemMessage != null) {
                errorMessage = problemMessage;
            }
        } else if (ex.getMessage() != null) {
            if (ex.getMessage().contains("JSON parse error")) {
                errorMessage = "Invalid JSON format";
            }
        }

        body.put("errors", errorMessage);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // database integrity violation
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> body = new HashMap<>();

        String errorMessage = "Database integrity violation";

        if (ex.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) ex.getCause();
            String constraintName = cve.getMessage();

            if (constraintName != null && constraintName.toLowerCase().contains("uk")) {
                errorMessage = "Duplicate value violates unique constraint";
            }
        }

        body.put("error", errorMessage);
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
