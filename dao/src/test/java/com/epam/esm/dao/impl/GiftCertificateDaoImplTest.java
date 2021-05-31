package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateRepository;
import com.epam.esm.dao.config.TestConfig;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.model.dto.GiftCertificateDto;
import com.epam.esm.model.dto.StateCertificate;
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
@ActiveProfiles("testDao")
@ContextConfiguration(classes = TestConfig.class)
@Sql({"classpath:drop.sql", "classpath:schema.sql", "classpath:data.sql"})
class GiftCertificateDaoImplTest {
    @Autowired
    private GiftCertificateRepository giftCertificateRepository;


    @Test
    @Transactional
    void findEntityByIdExistCertificate() {
        Assert.assertNotNull(giftCertificateRepository.findById(1L));
    }

    @Test
    @Transactional
    void findEntityByIdNotExistCertificate() {
        Optional<GiftCertificate> giftCertificate = this.giftCertificateRepository.findByName_AndState("giftCertificateDaoImpl", StateCertificate.ACTIVE);
    }

    @Test
    void findEntityByIdExistCertificateWithoutTags() {
        Assert.assertNotNull(giftCertificateRepository.findById(4L));
    }
}
