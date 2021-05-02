package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dao.TagDao;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CertificateServiceImplTest {
    @InjectMocks
    private CertificateService certificateService;
    @Mock
    GiftCertificateDao certificateDao;
    @Mock
    TagDao tagDao;
    GiftCertificateDto giftCertificate;

    @BeforeEach
    public void setUp() {
        certificateDao = Mockito.mock(GiftCertificateDaoImpl.class);
        tagDao = Mockito.mock(TagDaoImpl.class);
        certificateService = new CertificateServiceImpl(certificateDao, tagDao);
        giftCertificate = new GiftCertificateDto();
        giftCertificate.setId(7L);
        giftCertificate.setName("testName");
        giftCertificate.setDescription("testDescription");
        giftCertificate.setDuration(1);
        giftCertificate.setPrice(BigDecimal.valueOf(10.15));
        giftCertificate.setCreateDate(LocalDateTime.now());
        giftCertificate.setLastUpdateDate(LocalDateTime.now());
    }

    @Test
    void findEntityByIdPositive() throws RecourseNotExistException {
        long testId = giftCertificate.getId();
        Mockito.when(certificateDao.findEntityById(testId)).thenReturn(Optional.of(giftCertificate));
        assertEquals(giftCertificate, certificateService.findEntityById(testId));
    }

    @Test
    void findEntityByIdNegative() {
        long testId = giftCertificate.getId();
        Mockito.when(certificateDao.findEntityById(testId)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class, () -> certificateService.findEntityById(testId));
    }

    @Test
    void deleteByIdNegative() {
        long testId = giftCertificate.getId();
        Mockito.when(certificateDao.findEntityById(testId)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class, () -> certificateService.delete(testId));
    }

    @Test
    void addNegative() {
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setName("testCertificate");
        GiftCertificateDto findCertificateDto = new GiftCertificateDto();
        findCertificateDto.setName("testCertificate");
        findCertificateDto.setId(7L);
        Mockito.when(certificateDao.findByName(giftCertificateDto.getName())).thenReturn(Optional.of(findCertificateDto));
        assertThrows(RecourseExistException.class, () -> certificateService.add(giftCertificateDto));
    }

    @Test
    void add() throws RecourseNotExistException, RecourseExistException {
        long testId = 1L;
        TagDto tagDto = new TagDto(1L, "testTag");
        List<TagDto> tagDtoList = new ArrayList<>();
        tagDtoList.add(tagDto);
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(null, "test", "test", 10,
                BigDecimal.valueOf(10.2), null, null, tagDtoList);
        GiftCertificateDto giftCertificateCreate = new GiftCertificateDto(1L, "test", "test", 10,
                BigDecimal.valueOf(10.2), null, null, tagDtoList);
        Mockito.when(certificateDao.findByName(giftCertificateDto.getName())).thenReturn(Optional.empty());
        Mockito.when(tagDao.findByName(tagDto.getTagName())).thenReturn(Optional.of(tagDto));
        Mockito.when(certificateDao.create(giftCertificateDto)).thenReturn(giftCertificateCreate);
        GiftCertificateDto expected = new GiftCertificateDto(1L, "test", "test", 10,
                BigDecimal.valueOf(10.2), null, null, tagDtoList);
        assertEquals(expected, certificateService.add(giftCertificateDto));
    }
    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findGiftCertificateListByParams() {
    }
}
