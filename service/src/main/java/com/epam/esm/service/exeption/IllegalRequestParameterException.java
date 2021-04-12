package com.epam.esm.service.exeption;

import com.epam.esm.model.parameters.CustomErrorCode;

public class IllegalRequestParameterException extends Exception {
    CustomErrorCode customErrorCode;

    public IllegalRequestParameterException(CustomErrorCode customErrorCode, String message) {
        super(message);
    }

    public IllegalRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRequestParameterException(String message, CustomErrorCode customErrorCode) {
        super(message);
        this.customErrorCode = customErrorCode;
    }

    public CustomErrorCode getCustomErrorCode() {
        return customErrorCode;
    }
}
