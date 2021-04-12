package com.epam.esm.service.exeption;

public class RecourseExistException extends Exception{
    public RecourseExistException(String message) {
        super(message);
    }

    public RecourseExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
