package com.epam.esm.model.parameters;

public enum CustomErrorCode {
    RECOURSE_TAG_EXIST("40901"),
    RECOURSE_CERTIFICATE_EXIST("40902"),
    RECOURSE_USER_EXIST("40903"),
    RECOURSE_PURCHASE_EXIST("40904"),
    RECOURSE_TAG_NOT_EXIST("40401"),
    RECOURSE_CERTIFICATE_NOT_EXIST("40402"),
    RECOURSE_PURCHASE_NOT_EXIST("40404"),
    RECOURSE_USER_NOT_EXIST("40403"),
    ILLEGAL_REQUEST_PARAMETER("40001"),
    ILLEGAL_SORT_PARAMETER("40002"),
    ERROR_VALIDATION("40003"),
    ERROR_SQL("50001"),
    ERROR_JSON("40005");
    private String statusCode;

    CustomErrorCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

}
