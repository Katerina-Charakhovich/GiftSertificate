package com.epam.esm.web.advice;

public enum CustomErrorCode {
    RECOURSE_EXIST("40901"),
    RECOURSE_NOT_EXIST("40401"),
    ILLEGAL_REQUEST_PARAMETER("40001"),
    ILLEGAL_SORT_PARAMETER("40002"),
    ERROR_VALIDATION("40003"),
    ERROR_SQL("40004"),
    ERROR_JSON("40005");

    private String statusCode;

    CustomErrorCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
