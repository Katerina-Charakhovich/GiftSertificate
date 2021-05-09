package com.epam.esm.model.parameters;

import java.util.Arrays;
import java.util.Optional;

public enum CertificateParameter {
    TAG_NAME("tagName", TableName.TABLE_TAG),
    CERTIFICATE_NAME("name", TableName.TABLE_CERTIFICATE),
    CERTIFICATE_DESCRIPTION("description", TableName.TABLE_CERTIFICATE),
    CREATE_DATE("createDate", TableName.TABLE_CERTIFICATE);
    String paramName;
    String entityName;

    CertificateParameter(String paramName, String entityName) {
        this.paramName = paramName;
        this.entityName = entityName;
    }

    public String getParamName() {
        return paramName;
    }

    public String getEntityName() {
        return entityName;
    }

    public static Optional<CertificateParameter> getByName(String name) {
        return Arrays
                .stream(CertificateParameter.values())
                .filter(s -> s.getParamName().equals(name)).findFirst();
    }
}
