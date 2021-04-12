package com.epam.esm.service.exeption;

public class IllegalRequestSortParameterException extends Exception {
    public IllegalRequestSortParameterException(String message) {
        super(message);
    }

    public IllegalRequestSortParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
