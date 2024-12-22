package org.movieverse.movieverse_backend.exception;

import org.movieverse.movieverse_backend.common.ApiResponse;
import org.movieverse.movieverse_backend.exception.custom.ResourceAlreadyExistsException;
import org.movieverse.movieverse_backend.exception.custom.ResourceNotFoundException;
import org.movieverse.movieverse_backend.exception.custom.TokenExpiredException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handle(ResourceNotFoundException exp) {
        ApiResponse<?> apiResponse = new ApiResponse<>(
                exp.getMessage(), null);

        return ResponseEntity.status(NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ApiResponse<?>> handle(TokenExpiredException exp) {
        ApiResponse<?> apiResponse = new ApiResponse<>(
                exp.getMessage(), null);

        return ResponseEntity.status(BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        ApiResponse<?> apiResponse = new ApiResponse<>(ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ApiResponse<?> apiResponse = new ApiResponse<>(errorMessage, null);

        return ResponseEntity.status(BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception exp) {
        ApiResponse<?> apiResponse = new ApiResponse<>(
                exp.getMessage(), null);

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}

