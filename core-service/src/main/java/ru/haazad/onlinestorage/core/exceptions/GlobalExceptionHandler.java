package ru.haazad.onlinestorage.core.exceptions;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.haazad.onlinestorage.api.exceptions.ApplicationError;
import ru.haazad.onlinestorage.api.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ApplicationError(HttpStatus.NOT_FOUND.toString(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchSQLException(PSQLException e) {
        return new ResponseEntity<>(new ApplicationError(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
