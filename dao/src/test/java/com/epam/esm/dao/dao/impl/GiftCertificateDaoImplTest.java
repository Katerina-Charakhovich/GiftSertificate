package com.epam.esm.dao.dao.impl;

import com.epam.esm.dao.mapper.CertificateExtractor;
import com.epam.esm.dao.mapper.CertificateMapper;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

class GiftCertificateDaoImplTest {

    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private GiftCertificateDaoImpl giftCertificateDaoImpl;

    @BeforeEach
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql").addScript("classpath:data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        giftCertificateDaoImpl = new GiftCertificateDaoImpl(jdbcTemplate,new CertificateMapper(),new CertificateExtractor());
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    void findEntityByIdExistCertificate() {
        Assert.assertNotNull(giftCertificateDaoImpl.findEntityById(1));
    }
    @Test
    void findEntityByIdNotExistCertificate() {
        Assert.assertTrue(giftCertificateDaoImpl.findEntityById(15).isEmpty());
    }
    @Test
    void findEntityByIdExistCertificateWithoutTags() {
        Assert.assertNotNull(giftCertificateDaoImpl.findEntityById(4));
    }

    @Test
    void delete() {
        long testId=1;
        giftCertificateDaoImpl.delete(1);
        Assert.assertTrue(giftCertificateDaoImpl.findEntityById(1).isEmpty());
    }

    @Test
    void deleteLinkTagById() {
        long testId=1;
        giftCertificateDaoImpl.deleteLinkTagById(1);
        Assert.assertEquals(0, giftCertificateDaoImpl.findEntityById(1).get().getListTag().size());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void testDelete() {
    }

}