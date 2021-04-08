package com.epam.esm.web.advice;

public enum CustomErrorCode {
    RECOURSE_EXIST("404001"),
    RECOURSE_NOT_EXIST("401002"),
    ILLEGAL_REQUEST_PARAMETER("400001"),
    ILLEGAL_SORT_PARAMETER("400002"),
    ERROR_VALIDATION("500001");
    private String statusCode;

    CustomErrorCode(String statusCode) {
        this.statusCode=statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
