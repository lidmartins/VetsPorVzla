package com.vetsportvzla.backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        final String correlationId = MDC.get("correlationId");
        log.warn("Unauthorized access attempt for correlation-id: {}", correlationId);

        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false),
                correlationId
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        // The correlationId is automatically added to the log message by the Logback pattern.
        // We retrieve it here to include it in the response body.
        final String correlationId = MDC.get("correlationId");
        log.error("An unexpected error occurred for correlation-id: {}", correlationId, ex);

        // Find the root cause of the exception to provide a more specific message
        Throwable rootCause = ex;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }

        // Create a detailed error response for the client, including the correlation ID
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                rootCause.getMessage(),
                request.getDescription(false),
                correlationId
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private String correlationId;

    public ErrorDetails(Date timestamp, String message, String details, String correlationId) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.correlationId = correlationId;
    }

    // Getters and Setters
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
