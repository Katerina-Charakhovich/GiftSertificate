package com.epam.esm.model.parameters;

import java.util.Arrays;
import java.util.Optional;

public enum CertificateSortType {
    SORT_BY_NAME(CertificateApiParameter.CERTIFICATE_NAME),
    SORT_BY_CREATE_DATE(CertificateApiParameter.CREATE_DATE);

    CertificateApiParameter type;

    CertificateSortType(CertificateApiParameter type) {
        this.type = type;
    }

    public CertificateApiParameter getType() {
        return type;
    }

    public void setType(CertificateApiParameter type) {
        this.type = type;
    }

    public static Optional<CertificateSortType> getByType(String type) {
        return Arrays
                .stream(CertificateSortType.values())
                .filter(s -> s.getType().getParamName().toUpperCase().equals(type.toUpperCase())).findFirst();
    }
}
