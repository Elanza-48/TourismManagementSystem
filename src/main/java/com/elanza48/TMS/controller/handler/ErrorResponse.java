package com.elanza48.TMS.controller.handler;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Arrays;
import java.util.List;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class ErrorResponse {

    private HttpStatus status;
    private String message;

    private List<String> errors;

    public ErrorResponse(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorResponse(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}