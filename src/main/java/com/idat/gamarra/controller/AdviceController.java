package com.idat.gamarra.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idat.gamarra.exception.NotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        log.warn("NOT_FOUND: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().category("NOT_FOUND")
                .message("Not Found").status(404).description(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        log.warn("BAD_REQUEST: {}", "Validation Errors.");
        return new ResponseEntity<>(ErrorResponse.builder().status(400).category("BAD_REQUEST").message("Validation Errors")
                .description("The request parameters failed to validate.").details(errors).build(), HttpStatus.BAD_REQUEST);
    }

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorResponse {
        private Integer status;
        private String category;
        private String message;
        private String description;
        private List<String> details;
    }
}
