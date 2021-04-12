package com.epam.esm.model.parameters;

import java.util.Arrays;
import java.util.Optional;

public enum CertificateParameter {
    TAG_NAME("tagName", TableColumnName.TAG_DAO_NAME),
    CERTIFICATE_NAME("name", TableColumnName.CERTIFICATE_DAO_NAME),
    CERTIFICATE_DESCRIPTION("description", TableColumnName.CERTIFICATE_DAO_DESCRIPTION),
    CREATE_DATE("createDate", TableColumnName.CERTIFICATE_DAO_CREATE_DATE);
    String name;
    String tableColumn;

    CertificateParameter(String name, String tableColumn) {
        this.name = name;
        this.tableColumn = tableColumn;
    }

    public String getName() {
        return name;
    }

    public String getTableColumn() {
        return tableColumn;
    }

    public static Optional<CertificateParameter> getByName(String name) {
        return Arrays
                .stream(CertificateParameter.values())
                .filter(s -> s.getName().equals(name)).findFirst();
    }
}
