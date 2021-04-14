package com.epam.esm.model.parameters;

/**
 * The type Column name.
 * Is used for store the database's field's names.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
public class TableColumnName {
    public static final String TAG_DAO_ID = "tag.id_tag";
    public static final String TAG_DAO_NAME = "tag.name_tag";
    public static final String CERTIFICATE_DAO_ID = "certificate.certificate_id";
    public static final String CERTIFICATE_DAO_NAME = "certificate.certificate_name";
    public static final String CERTIFICATE_DAO_DESCRIPTION = "certificate.description";
    public static final String CERTIFICATE_DAO_PRICE = "certificate.price";
    public static final String CERTIFICATE_DAO_DURATION = "certificate.duration";
    public static final String CERTIFICATE_DAO_CREATE_DATE = "certificate.create_date";
    public static final String CERTIFICATE_DAO_LAST_UPDATE_DATE = "certificate.last_update_date";
    public static final String CERTIFICATE_TAG_DAO_CERTIFICATE_ID = "certificate_tag.certificate_id";
    public static final String CERTIFICATE_TAG_DAO_TAG_ID = "certificate_tag.id_tag";

    TableColumnName() {
    }
}
