package com.epam.esm.service.exeption;

import com.epam.esm.model.parameters.CustomErrorCode;

public class IllegalRequestParameterException extends Exception {
    CustomErrorCode customErrorCode;

    public IllegalRequestParameterException(CustomErrorCode customErrorCode, String message) {
        super(message);
        this.customErrorCode=customErrorCode;
    }

    public IllegalRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomErrorCode getCustomErrorCode() {
        return customErrorCode;
    }
}
