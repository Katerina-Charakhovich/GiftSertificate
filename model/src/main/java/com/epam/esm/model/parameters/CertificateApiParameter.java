package com.epam.esm.model.parameters;

import java.util.Arrays;
import java.util.Optional;

public enum CertificateApiParameter {
    TAG_NAME("tagName", TableColumnName.TAG_DAO_NAME),
    CERTIFICATE_NAME("name", TableColumnName.CERTIFICATE_DAO_NAME),
    CERTIFICATE_DESCRIPTION("description", TableColumnName.CERTIFICATE_DAO_DESCRIPTION),
    CREATE_DATE("createDate", TableColumnName.CERTIFICATE_DAO_CREATE_DATE);

    String paramName;
    String paramValue;

    CertificateApiParameter(String paramName, String paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public String getParamName() {
        return paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public static Optional<CertificateApiParameter> getByName (String name) {
        return Arrays
                .stream(CertificateApiParameter.values())
                .filter(s -> s.getParamName().equals(name)).findFirst();
    }
}
