package com.epam.esm.dao.dao.impl;

import com.epam.esm.dao.dao.BaseDao;
import com.epam.esm.dao.dao.GiftCertificateDao;
import com.epam.esm.dao.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GiftCertificateDaoImpl extends BaseDao<GiftCertificate> implements GiftCertificateDao {
    private final JdbcTemplate jdbcTemplate;
    private static final String FIND_TAG_BY_ID="select name_tag,id_tag from tag where id_tag=?";

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<GiftCertificate> findEntityById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int create(GiftCertificate entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(GiftCertificate entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
