package com.idat.gamarra.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Long id) {
        super(String.format("ID %d not found.", id));
    }
}
