package com.epam.esm.service.exeption;

import com.epam.esm.model.parameters.CustomErrorCode;

public class RecourseExistException extends Exception {
    CustomErrorCode customErrorCode;

    public RecourseExistException(String message) {
        super(message);
    }

    public RecourseExistException(CustomErrorCode customErrorCode, String message) {
        super(message);
        this.customErrorCode = customErrorCode;
    }

    public RecourseExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomErrorCode getCustomErrorCode() {
        return customErrorCode;
    }
}
