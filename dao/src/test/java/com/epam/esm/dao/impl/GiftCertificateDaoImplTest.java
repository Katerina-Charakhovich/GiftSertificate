package com.epam.esm.dao.impl;

import com.epam.esm.dao.config.TestConfig;
import com.epam.esm.model.dto.GiftCertificateDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = TestConfig.class)
@Sql({"classpath:drop.sql", "classpath:schema.sql", "classpath:data.sql"})
class GiftCertificateDaoImplTest {
    @Autowired
    private GiftCertificateDaoImpl giftCertificateDaoImpl;


    @Test
    @Transactional
    void findEntityByIdExistCertificate() {
        Assert.assertNotNull(giftCertificateDaoImpl.findEntityById(1));
    }

    @Test
    @Transactional
    void findEntityByIdNotExistCertificate() {
        Optional<GiftCertificateDto> giftCertificateDaoImpl = this.giftCertificateDaoImpl.findByName("giftCertificateDaoImpl");
    }

    @Test
    void findByName() {
    }

  /*  @Test
    void findEntityByIdExistCertificateWithoutTags() {
        Assert.assertNotNull(giftCertificateDaoImpl.findEntityById(4));
    }*/

   /* @Test
    @Transactional
    void delete() {
       /* long testId = 1;
        giftCertificateDaoImpl.delete(testId);
        Assert.assertFalse(giftCertificateDaoImpl.findEntityById(testId).isPresent());
    }*/

    /*@Test
    void deleteLinkTagById() {
        long testId = 1;
        giftCertificateDaoImpl.deleteLinkTagById(testId);
        List<TagDto> listTag = giftCertificateDaoImpl.findEntityById(testId).get().getListTag();
        int y = giftCertificateDaoImpl.findEntityById(testId).get().getListTag().size();
        Assert.assertEquals(0, giftCertificateDaoImpl.findEntityById(testId).get().getListTag().size());
    }*/

  /*  @Test
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
    }*/

  /*  @Test
    void update() {
        GiftCertificateDto giftCertificate = giftCertificateDaoImpl.findEntityById(1).get();
        giftCertificate.setName("updateName");
        giftCertificateDaoImpl.update(giftCertificate);
        Assert.assertEquals(giftCertificate.getName(), giftCertificateDaoImpl.findEntityById(1).get().getName());
    }*/
}
