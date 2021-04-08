package com.epam.esm.service.exeption;

public class IllegalRequestParameterException extends Exception{
    public IllegalRequestParameterException(String message) {
        super(message);
    }
}
