package com.epam.esm.dao.dao.impl;

import com.epam.esm.dao.dao.GiftCertificateDao;
import com.epam.esm.dao.mapper.CertificateExtractor;
import com.epam.esm.dao.mapper.CertificateMapper;
import com.epam.esm.model.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao{
    private final JdbcTemplate jdbcTemplate;
    private final CertificateMapper certificateMapper;
    private final CertificateExtractor certificateExtractor;

    private static final String FIND_CERTIFICATE_BY_ID="select t.certificate_id,t.certificate_name,t.description," +
            "t.price,t.duration,t.create_date,t.last_update_date," +
            " t2.id_tag,t2.name_tag" +
            " from certificate t " +
            " left  join certificate_tag t1 on t.certificate_id=t1.certificate_id " +
            " left join tag t2 on t1.id_tag=t2.id_tag " +
            " where t.certificate_id=?";
    private static final String DELETE_LINK_TAG_BY_ID="delete from certificate_tag where certificate_id=?";
    private static final String DELETE_BY_ID="delete from certificate where certificate_id=?";

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate, CertificateMapper certificateMapper, CertificateExtractor certificateExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.certificateMapper = certificateMapper;
        this.certificateExtractor = certificateExtractor;
    }

    @Override
    public Optional<GiftCertificate> findEntityById(long id) {
        Optional<GiftCertificate> optionalCertificate = Optional.empty();
        List <GiftCertificate> certificateList= jdbcTemplate.query( FIND_CERTIFICATE_BY_ID, certificateExtractor, id);
        return certificateList.isEmpty() ? Optional.empty() : Optional.of(certificateList.get(0));
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE_BY_ID,id)>0;
    }
    @Override
    public boolean deleteLinkTagById(long id) {
        return jdbcTemplate.update(DELETE_LINK_TAG_BY_ID,id)>0;
    }
    @Override
    public GiftCertificate create(GiftCertificate entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(GiftCertificate entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
