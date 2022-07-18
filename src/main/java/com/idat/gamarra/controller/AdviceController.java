package com.idat.gamarra.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idat.gamarra.exception.InvalidDeleteException;
import com.idat.gamarra.exception.InvalidSaleException;
import com.idat.gamarra.exception.InvalidUpdateException;
import com.idat.gamarra.exception.NotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class AdviceController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        log.warn("BAD_REQUEST: {}", "Malformed JSON request");
        return new ResponseEntity<>(ErrorResponse.builder().status(400).category("BAD_REQUEST")
                .message("El JSON no es correcto para esta petición.").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        log.warn("NOT_FOUND: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().category("NOT_FOUND")
                .message("Not Found").status(404).description(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidSaleException.class)
    public ResponseEntity<Object> handleInvalidSaleException(InvalidSaleException ex) {
        log.warn("BAD_REQUEST: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().category("BAD_REQUEST")
                .message("Invalid Sale").status(400).description(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidDeleteException.class, InvalidUpdateException.class})
    public ResponseEntity<Object> handleInvalidDeleteException(RuntimeException ex) {
        log.warn("BAD_REQUEST: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().category("BAD_REQUEST")
                .message("Invalid Delete").status(400).description(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class, HttpServerErrorException.class})
    public ResponseEntity<Object> handleServerErrorException(Exception ex) {
        log.warn("INTERNAL_SERVER_ERROR: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().category("INTERNAL_SERVER_ERROR")
                .message("Internal Server Error").status(500).description(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
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
