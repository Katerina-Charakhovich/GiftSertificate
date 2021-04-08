package com.epam.esm.model.parameters;

import java.util.Arrays;
import java.util.Optional;

public enum CertificateSearchParameter {
    TAG_NAME(CertificateParameter.TAG_NAME),
    CERTIFICATE_NAME(CertificateParameter.CERTIFICATE_NAME),
    CERTIFICATE_DESCRIPTION(CertificateParameter.CERTIFICATE_DESCRIPTION);
    CertificateParameter certificateParameter;

    CertificateSearchParameter(CertificateParameter certificateParameter) {
        this.certificateParameter = certificateParameter;
    }
    public static Optional<CertificateSearchParameter> getByType(String type) {
        return Arrays
                .stream(CertificateSearchParameter.values())
                .filter(s -> s.certificateParameter.getName().toUpperCase().equals(type.toUpperCase())).findFirst();
    }
}
