package com.yang.nbaplayerapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class PlayerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    public final ResponseEntity<Object> handlePlayerException(
            PlayerNotFoundException playerNotFoundException, WebRequest request) {

        Instant timestamp = Instant.now();
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(playerNotFoundException.getMessage(),
                request.getDescription(false), timestamp);
        return new ResponseEntity<>(apiErrorMessage, playerNotFoundException.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handlePlayerGeneralException(Exception ex, WebRequest request) {

        Instant timestamp = Instant.now();
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getLocalizedMessage(),
                request.getDescription(false), timestamp);
        return new ResponseEntity<>(apiErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Instant timestamp = Instant.now();
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getLocalizedMessage(),
                request.getDescription(false), timestamp);
        return new ResponseEntity<>(apiErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Instant timestamp = Instant.now();
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getLocalizedMessage(),
                request.getDescription(false), timestamp);
        return new ResponseEntity<>(apiErrorMessage, status);
    }
}
