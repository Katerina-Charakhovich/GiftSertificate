package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateRepository;
import com.epam.esm.dao.PurchaseRepository;
import com.epam.esm.dao.TagRepository;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.model.dto.*;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CertificateServiceImplTest {
    @InjectMocks
    private CertificateService certificateService;
    @Mock
    GiftCertificateRepository giftCertificateRepository;
    @Mock
    TagRepository tagRepository;
    @Mock
    PurchaseRepository purchaseRepository;

    GiftCertificate giftCertificate;
    GiftCertificateDto giftCertificateDto;

    @BeforeEach
    public void setUp() {
        giftCertificateRepository = Mockito.mock(GiftCertificateRepository.class);
        tagRepository = Mockito.mock(TagRepository.class);
        purchaseRepository = Mockito.mock(PurchaseRepository.class);
        certificateService = new CertificateServiceImpl(giftCertificateRepository, tagRepository, purchaseRepository);
        giftCertificate = new GiftCertificate();
        giftCertificate.setId(7L);
        giftCertificate.setName("testName");
        giftCertificate.setDescription("testDescription");
        giftCertificate.setDuration(1);
        giftCertificate.setPrice(BigDecimal.valueOf(10.15));
        giftCertificate.setCreateDate(LocalDateTime.now());
        giftCertificate.setLastUpdateDate(giftCertificate.getCreateDate());
        giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setId(7L);
        giftCertificateDto.setName("testName");
        giftCertificateDto.setDescription("testDescription");
        giftCertificateDto.setDuration(1);
        giftCertificateDto.setPrice(BigDecimal.valueOf(10.15));
        giftCertificateDto.setCreateDate(giftCertificate.getCreateDate());
        giftCertificateDto.setLastUpdateDate(giftCertificate.getCreateDate());
    }

    @Test
    void findEntityByIdPositive() throws RecourseNotExistException {
        long testId = giftCertificate.getId();
        Mockito.when(giftCertificateRepository.findById(testId)).thenReturn(Optional.of(giftCertificate));
        assertEquals(giftCertificateDto, certificateService.findEntityById(testId));
    }

    @Test
    void findEntityByIdNegative() {
        long testId = giftCertificate.getId();
        Mockito.when(giftCertificateRepository.findById(testId)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class, () -> certificateService.findEntityById(testId));
    }

    @Test
    void deleteByIdNegative() {
        long testId = giftCertificate.getId();
        Mockito.when(giftCertificateRepository.findById(testId)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class, () -> certificateService.delete(testId));
    }

    @Test
    void addNegative() {
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setName("testCertificate");
        GiftCertificate findCertificate = new GiftCertificate();
        findCertificate.setName("testCertificate");
        findCertificate.setId(7L);
        Mockito.when(giftCertificateRepository.findByName_AndState(giftCertificateDto.getName(),StateCertificate.ACTIVE)).thenReturn(Optional.of(findCertificate));
        assertThrows(RecourseExistException.class, () -> certificateService.add(giftCertificateDto));
    }
}
