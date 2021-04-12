package com.epam.esm.model.parameters;

import java.util.Arrays;
import java.util.Optional;

public enum CustomErrorCode {
    RECOURSE_TAG_EXIST("40901"),
    RECOURSE_CERTIFICATE_EXIST("40902"),
    RECOURSE_TAG_NOT_EXIST("40401"),
    RECOURSE_CERTIFICATE_NOT_EXIST("40402"),
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
    public static Optional<CertificateSortType> getByType(String type) {
        return Arrays
                .stream(CertificateSortType.values())
                .filter(s -> s.getType().getName().equalsIgnoreCase(type)).findFirst();
    }
}
