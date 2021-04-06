package com.epam.esm.service.impl;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.config.ServiceConfig;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

@SpringJUnitConfig(ServiceConfig.class)

public class Temp {
    @Autowired
    CertificateService certificateService;

    @Test
    void findEntityByIdPositive() throws RecourseNotExistException {
        GiftCertificate giftCertificate=certificateService.findEntityById(1);
        int r=1;
    }

}
