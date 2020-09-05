package com.jsglobe.toys.api;

import com.jsglobe.toys.api.model.ErrorInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2(topic = "Errors")
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        log.error(exception);
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "unknown-error",
                "something went wrong."
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        log.error(exception);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal-error", "internal error.");
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        log.error(exception);
        return buildResponse(HttpStatus.NOT_FOUND, "not-found", "resource was not found.");
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String code, String message) {
        final var errorInfo = ErrorInfo.create(code, message);
        return ResponseEntity.status(status).body(errorInfo);
    }

}
