package com.epam.esm.service.exeption;

public class IllegalRequestParameters extends Exception{
    public IllegalRequestParameters(String message) {
        super(message);
    }
}
