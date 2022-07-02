package com.example.student.Exception;

public class RegistrationError extends RuntimeException{

    public RegistrationError(String message) {
        super(message);
    }
}