package com.epam.esm.dao.mapper;

import com.epam.esm.dao.dao.TableColumnName;
import com.epam.esm.model.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * The  Certificate RowMapper.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@Component
public class CertificateMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(rs.getLong(TableColumnName.CERTIFICATE_DAO_ID));
        certificate.setName(rs.getString(TableColumnName.CERTIFICATE_DAO_NAME));
        certificate.setName(rs.getString(TableColumnName.CERTIFICATE_DAO_NAME));
        certificate.setDescription(rs.getString(TableColumnName.CERTIFICATE_DAO_DESCRIPTION));
        certificate.setDuration(rs.getInt(TableColumnName.CERTIFICATE_DAO_DURATION));
        certificate.setPrice(rs.getBigDecimal(TableColumnName.CERTIFICATE_DAO_PRICE));
        certificate.setCreateDate(rs.getTimestamp(TableColumnName.CERTIFICATE_DAO_CREATE_DATE).toLocalDateTime());
        certificate.setLastUpdateDate(rs.getTimestamp(TableColumnName.CERTIFICATE_DAO_LAST_UPDATE_DATE).
                toLocalDateTime());
        return certificate;
    }
}
