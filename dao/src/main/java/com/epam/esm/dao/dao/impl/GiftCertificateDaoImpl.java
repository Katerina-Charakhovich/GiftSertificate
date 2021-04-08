package com.epam.esm.dao.dao.impl;

import com.epam.esm.dao.builder.BuilderSql;
import com.epam.esm.dao.dao.GiftCertificateDao;
import com.epam.esm.dao.mapper.CertificateExtractor;
import com.epam.esm.dao.mapper.CertificateMapper;
import com.epam.esm.model.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private final JdbcTemplate jdbcTemplate;
    private final CertificateMapper certificateMapper;
    private final CertificateExtractor certificateExtractor;

    private static final String FIND_CERTIFICATE_BY_ID = "SELECT certificate.certificate_id,certificate.certificate_name," +
            "certificate.description," +
            "certificate.price,certificate.duration,certificate.create_date,certificate.last_update_date," +
            " tag.id_tag,tag.name_tag" +
            " FROM certificate " +
            " LEFT JOIN certificate_tag ON certificate.certificate_id=certificate_tag.certificate_id " +
            " LEFT JOIN tag ON certificate_tag.id_tag=tag.id_tag " +
            " WHERE certificate.certificate_id=?";
    private static final String DELETE_LINK_TAG_BY_ID = "DELETE FROM certificate_tag WHERE certificate_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM certificate WHERE certificate.certificate_id=?";
    private static final String ADD_NEW_CERTIFICATE = "INSERT INTO certificate  " +
            "(certificate_name,description,price, duration ,create_date,last_update_date ) VALUES  (?,?,?,?,?,?)";
    private static final String ADD_LINK_ONE_TAG = "INSERT INTO certificate_tag " +
            "(certificate_id,id_tag ) VALUES (?,?)";
    private static final String FIND_CERTIFICATES_BY_PARAMS = "SELECT certificate.certificate_id," +
            "certificate.certificate_name,certificate.description,certificate.price,certificate.duration," +
            "certificate.create_date,certificate.last_update_date, " +
            "tag.id_tag,tag.name_tag FROM certificate " +
            "LEFT JOIN  certificate_tag ON certificate.certificate_id=certificate_tag.certificate_id " +
            "LEFT JOIN tag ON tag.id_tag=certificate_tag.id_tag";
    public static final String UPDATE_CERTIFICATE = "UPDATE certificate SET "
            + "certificate.certificate_name = ?, certificate.description = ?, certificate.price = ?," +
            " certificate.duration = ?, certificate.create_date = ?, "
            + "last_update_date = ? WHERE certificate.certificate_id = ?";
    private static final String FIND_BY_NAME = "SELECT certificate.certificate_id,certificate.certificate_name, " +
            "certificate.description, certificate.price,certificate.duration,certificate.create_date, " +
            "certificate.last_update_date, " +
            " tag.id_tag,tag.name_tag" +
            " FROM certificate " +
            " LEFT JOIN certificate_tag ON certificate.certificate_id=certificate_tag.certificate_id " +
            " LEFT JOIN tag ON certificate_tag.id_tag=tag.id_tag " +
            " WHERE certificate.certificate_name=?";

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate, CertificateMapper certificateMapper, CertificateExtractor certificateExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.certificateMapper = certificateMapper;
        this.certificateExtractor = certificateExtractor;
    }

    @Override
    public Optional<GiftCertificate> findEntityById(long id) {
        List<GiftCertificate> certificateList = jdbcTemplate.query(FIND_CERTIFICATE_BY_ID, certificateExtractor, id);
        assert certificateList != null;
        return certificateList.isEmpty() ? Optional.empty() : Optional.of(certificateList.get(0));
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id) > 0;
    }

    @Override
    public boolean deleteLinkTagById(long id) {
        return jdbcTemplate.update(DELETE_LINK_TAG_BY_ID, id) > 0;
    }

    @Override
    public boolean addLinkTag(long certificateId, long tagId) {
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_LINK_ONE_TAG);
            preparedStatement.setLong(1, certificateId);
            preparedStatement.setLong(2, tagId);
            return preparedStatement;
        };
        return jdbcTemplate.update(preparedStatementCreator) > 0;
    }

    @Override
    public List<GiftCertificate> findEntityByParams(Map<String, String> groupParams) {
        String sql = FIND_CERTIFICATES_BY_PARAMS +
                BuilderSql.buildFindAndSortCertificateByParameter(groupParams);
        return jdbcTemplate.query(sql, certificateExtractor);

    }

    @Override
    public Optional<GiftCertificate> findByName(String certificateName) {
        List<GiftCertificate> certificateList = jdbcTemplate.query(FIND_BY_NAME, certificateExtractor, certificateName);
        assert certificateList != null;
        return certificateList.isEmpty() ? Optional.empty() : Optional.of(certificateList.get(0));
    }

    @Override
    public GiftCertificate create(GiftCertificate entity) {
        KeyHolder key = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_CERTIFICATE,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setBigDecimal(3, entity.getPrice());
            preparedStatement.setInt(4, entity.getDuration());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, key);
        Number id = key.getKey();
        if (id != null) {
            entity.setId(id.longValue());
        }
        return entity;
    }

    @Override
    public GiftCertificate update(GiftCertificate entity) {
        jdbcTemplate.update(UPDATE_CERTIFICATE, entity.getName(), entity.getDescription(),
                entity.getPrice(), entity.getDuration(), Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), entity.getId());
        return entity;
    }

}


