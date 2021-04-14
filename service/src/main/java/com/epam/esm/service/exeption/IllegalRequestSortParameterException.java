package com.epam.esm.service.exeption;

import com.epam.esm.model.parameters.CustomErrorCode;

public class IllegalRequestSortParameterException extends Exception {
    CustomErrorCode customErrorCode;

    public IllegalRequestSortParameterException(String message) {
        super(message);
    }

    public IllegalRequestSortParameterException(CustomErrorCode customErrorCode, String message) {
        super(message);
        this.customErrorCode=customErrorCode;
    }

    public IllegalRequestSortParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomErrorCode getCustomErrorCode() {
        return customErrorCode;
    }
}
