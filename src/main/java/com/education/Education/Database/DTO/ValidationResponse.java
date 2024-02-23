package com.education.Education.Database.DTO;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ValidationResponse {
    private HttpStatus status;
    private String message;
    private Map<String, String> validationErrors;

    public ValidationResponse(HttpStatus status, String message, Map<String, String> validationErrors) {
        this.status = status;
        this.message = message;
        this.validationErrors = validationErrors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}
