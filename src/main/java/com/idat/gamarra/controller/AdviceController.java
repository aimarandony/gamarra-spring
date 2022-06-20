package com.idat.gamarra.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idat.gamarra.exception.NotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        log.warn("NOT_FOUND: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().category("NOT_FOUND")
                .message("Not Found").status(404).description(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
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
