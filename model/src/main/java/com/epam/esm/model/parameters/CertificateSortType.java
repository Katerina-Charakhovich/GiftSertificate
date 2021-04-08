package com.epam.esm.model.parameters;

import java.util.Arrays;
import java.util.Optional;

public enum CertificateSortType {
    SORT_BY_NAME(CertificateParameter.CERTIFICATE_NAME),
    SORT_BY_CREATE_DATE(CertificateParameter.CREATE_DATE);

    CertificateParameter type;

    CertificateSortType(CertificateParameter type) {
        this.type = type;
    }

    public CertificateParameter getType() {
        return type;
    }

    public void setType(CertificateParameter type) {
        this.type = type;
    }

    public static Optional<CertificateSortType> getByType(String type) {
        return Arrays
                .stream(CertificateSortType.values())
                .filter(s -> s.getType().getName().toUpperCase().equals(type.toUpperCase())).findFirst();
    }
}
