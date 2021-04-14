package com.epam.esm.dao.impl;

import com.epam.esm.dao.builder.BuilderSql;

import com.epam.esm.dao.converter.impl.GiftCertificateConverter;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.mapper.CertificateExtractor;
import com.epam.esm.dao.mapper.CertificateMapper;
import com.epam.esm.model.entity.GiftCertificateDto;
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
    private final CertificateExtractor certificateExtractor;
    private final GiftCertificateConverter certificateConverter;
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
            " certificate.duration = ?, "
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
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate,
                                  CertificateExtractor certificateExtractor,
                                  GiftCertificateConverter certificateConverter) {
        this.jdbcTemplate = jdbcTemplate;
        this.certificateExtractor = certificateExtractor;
        this.certificateConverter = certificateConverter;
    }

    @Override
    public Optional<GiftCertificateDto> findEntityById(long id) {
        List<GiftCertificate> certificateList = jdbcTemplate.query(FIND_CERTIFICATE_BY_ID, certificateExtractor, id);
        return certificateList.isEmpty()?Optional.empty(): Optional.ofNullable(certificateConverter.convertTo(certificateList.get(0)));
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
    public List<GiftCertificateDto> findEntityByParams(Map<String, String> groupParams) {
        String sql = FIND_CERTIFICATES_BY_PARAMS +
                BuilderSql.buildFindAndSortCertificateByParameter(groupParams);
        return certificateConverter.convertTo(jdbcTemplate.query(sql, certificateExtractor));
    }

    @Override
    public Optional<GiftCertificateDto> findByName(String certificateName) {
        List<GiftCertificate> certificateList = jdbcTemplate.query(FIND_BY_NAME, certificateExtractor, certificateName);
        return certificateList.isEmpty()?Optional.empty(): Optional.ofNullable(certificateConverter.convertTo(certificateList.get(0)));
    }

    @Override
    public GiftCertificateDto create(GiftCertificateDto entity) {
        GiftCertificate giftCertificate = certificateConverter.convertFrom(entity);
        KeyHolder key = new GeneratedKeyHolder();
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_CERTIFICATE,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, giftCertificate.getName());
            preparedStatement.setString(2, giftCertificate.getDescription());
            preparedStatement.setBigDecimal(3, giftCertificate.getPrice());
            preparedStatement.setInt(4, giftCertificate.getDuration());
            preparedStatement.setTimestamp(5, timestamp);
            preparedStatement.setTimestamp(6, timestamp);
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, key);
        Number id = key.getKey();
        if (id != null) {
            giftCertificate.setId(id.longValue());
        }
        return certificateConverter.convertTo(giftCertificate);
    }

    @Override
    public GiftCertificateDto update(GiftCertificateDto entity) {
        GiftCertificate giftCertificate = certificateConverter.convertFrom(entity);
        jdbcTemplate.update(UPDATE_CERTIFICATE, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getDuration(), Timestamp.valueOf(LocalDateTime.now()),
                giftCertificate.getId());
        return certificateConverter.convertTo(giftCertificate);
    }
}


