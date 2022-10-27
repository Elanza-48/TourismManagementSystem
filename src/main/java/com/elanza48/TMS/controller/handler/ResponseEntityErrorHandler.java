package com.elanza48.TMS.controller.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.Set;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ResponseEntityErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        StringBuilder builder = new StringBuilder();
        builder.append(" Supported method(s) are ");
        Set<HttpMethod> supportedMethods= e.getSupportedHttpMethods();
        if(supportedMethods!=null) supportedMethods.forEach(t -> builder.append(t + " "));

        ErrorResponse apiError = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED,
                e.getLocalizedMessage(), builder.toString());
        return new ResponseEntity<>( apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

        ErrorResponse apiError = new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedHandler(
            AccessDeniedException e, HttpServletRequest request){

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN,
                "Required higher privileges !", HttpStatus.FORBIDDEN.getReasonPhrase());
        return new ResponseEntity<>(errorResponse,errorResponse.getStatus());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> resourceNotFoundHandler(
            NoSuchElementException exception, HttpServletRequest request){

        ErrorResponse errorResponse = new ErrorResponse( HttpStatus.NOT_FOUND,
                "Resource doesn't exists !", HttpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<>(errorResponse,errorResponse.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> generalExceptionHandler(Exception e,  HttpServletRequest request){
        ErrorResponse errorResponse= new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getLocalizedMessage(), e.getMessage());

        return new ResponseEntity<>(errorResponse,errorResponse.getStatus());
    }
}
