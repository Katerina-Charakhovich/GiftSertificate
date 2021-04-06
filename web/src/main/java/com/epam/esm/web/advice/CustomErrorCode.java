package com.epam.esm.web.advice;

public enum CustomErrorCode {
    RECOURSE_EXIST("404001"),
    RECOURSE_NOT_EXIST("401002");
    private String statusCode;

    CustomErrorCode(String statusCode) {
        this.statusCode=statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
