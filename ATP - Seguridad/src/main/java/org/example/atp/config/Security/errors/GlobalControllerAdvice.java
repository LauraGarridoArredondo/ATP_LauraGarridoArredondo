package org.example.atp.config.Security.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.example.atp.config.Security.errors.exceptions.NewUserWithDifferentPasswordsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NewUserWithDifferentPasswordsException.class)
    public ResponseEntity<ApiError> handleNewUserErrors(Exception ex) {
        return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        ApiError  apiError = new ApiError(status, ex.getMessage());
        return ResponseEntity.status(status).headers(headers).body(apiError);
    }


    private ResponseEntity<ApiError> buildErrorResponseEntity(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(ApiError.builder()
                        .estado(status)
                        .mensaje(message)
                        .build());

    }


}