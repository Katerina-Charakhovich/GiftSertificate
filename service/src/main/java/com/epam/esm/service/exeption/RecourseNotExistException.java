package com.epam.esm.service.exeption;

import com.epam.esm.model.parameters.CustomErrorCode;

public class RecourseNotExistException extends Exception {
    CustomErrorCode customErrorCode;

    public RecourseNotExistException(String message) {
        super(message);
    }

    public RecourseNotExistException(CustomErrorCode customErrorCode, String message) {
        super(message);
        this.customErrorCode=customErrorCode;
    }

    public RecourseNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomErrorCode getCustomErrorCode() {
        return customErrorCode;
    }
}
