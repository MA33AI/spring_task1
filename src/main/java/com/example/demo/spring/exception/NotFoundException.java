package com.example.demo.spring.exception;

public class NotFoundException extends AppException{

    public NotFoundException(String message) {
        super(message);
    }
}
