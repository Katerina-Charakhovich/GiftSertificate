package com.epam.esm.dao.impl;

import com.epam.esm.dao.converter.impl.GiftCertificateConverter;
import com.epam.esm.dao.converter.impl.TagConverter;
import com.epam.esm.dao.mapper.CertificateExtractor;
import com.epam.esm.dao.mapper.CertificateMapper;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.model.entity.GiftCertificateDto;
import com.epam.esm.model.entity.TagDto;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

class GiftCertificateDaoImplTest {
    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private GiftCertificateDaoImpl giftCertificateDaoImpl;
    private GiftCertificateConverter certificateConverter;

    @BeforeEach
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql").addScript("classpath:data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        giftCertificateDaoImpl = new GiftCertificateDaoImpl(jdbcTemplate,
                new CertificateExtractor(new TagMapper(),new CertificateMapper()),
                new GiftCertificateConverter(new TagConverter())
        );
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
        Assert.assertFalse(giftCertificateDaoImpl.findEntityById(15).isPresent());
    }

    @Test
    void findEntityByIdExistCertificateWithoutTags() {
        Assert.assertNotNull(giftCertificateDaoImpl.findEntityById(4));
    }

    @Test
    void delete() {
        long testId = 1;
        giftCertificateDaoImpl.delete(testId);
        Assert.assertFalse(giftCertificateDaoImpl.findEntityById(testId).isPresent());
    }

    @Test
    void deleteLinkTagById() {
        long testId = 1;
        giftCertificateDaoImpl.deleteLinkTagById(testId);
        List<TagDto> listTag = giftCertificateDaoImpl.findEntityById(testId).get().getListTag();
        int y=giftCertificateDaoImpl.findEntityById(testId).get().getListTag().size();
        Assert.assertEquals(0, giftCertificateDaoImpl.findEntityById(testId).get().getListTag().size());
    }

    @Test
    void create() {
        GiftCertificateDto certificate = new GiftCertificateDto();
        certificate.setName("testName");
        certificate.setDescription("testDescription");
        certificate.setDuration(10);
        certificate.setPrice(BigDecimal.valueOf(10.02));
        certificate.setCreateDate(LocalDateTime.now());
        certificate.setLastUpdateDate(LocalDateTime.now());
        GiftCertificateDto createCertificate = giftCertificateDaoImpl.create(certificate);
        certificate.setId(createCertificate.getId());
        Assert.assertEquals(certificate, createCertificate);
    }

    @Test
    void update() {
        GiftCertificateDto giftCertificate = giftCertificateDaoImpl.findEntityById(1).get();
        giftCertificate.setName("updateName");
        giftCertificateDaoImpl.update(giftCertificate);
        Assert.assertEquals(giftCertificate.getName(), giftCertificateDaoImpl.findEntityById(1).get().getName());
    }
}