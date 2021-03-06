package com.epam.esm.service.impl;

import com.epam.esm.dao.*;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.entity.Purchase;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.entity.User;
import com.epam.esm.model.dto.*;
import com.epam.esm.service.PurchaseService;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchaseServiceImplTest {
    @InjectMocks
    private PurchaseService purchaseService;
    @Mock
    PurchaseRepository purchaseRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    GiftCertificateRepository giftCertificateRepository;

    @BeforeEach
    public void setUp() {
        purchaseRepository = Mockito.mock(PurchaseRepository.class);
        giftCertificateRepository = Mockito.mock(GiftCertificateRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        purchaseService = new PurchaseServiceImpl(userRepository, purchaseRepository, giftCertificateRepository);
    }

    @Test
    void add() throws RecourseNotExistException {
        long testId = 1L;
        UseShortDto userShortDto = new UseShortDto(1L, "test", "test");
        User user = new User(1L, "test", "test", null);
        User userAfterConvPurchase = new User(1L, null, null,null);
        TagDto tagDto = new TagDto(1L, "testTag");
        List<TagDto> tagDtoList = new ArrayList<>();
        tagDtoList.add(tagDto);
        Tag tag = new Tag(1L, "testTag");
        List<Tag> tagList = new ArrayList<>();
        tagList.add(tag);
        GiftCertificate giftCertificate = new GiftCertificate(1L, "test", "test", 10,
                BigDecimal.valueOf(10.2), null, null, StateCertificate.ACTIVE, tagList);
        GiftCertificate giftCertificateAfterConvPurchase = new GiftCertificate(1L, null, null, 0,
               null, null, null,null, null);
        List<GiftCertificate> giftCertificateList = new ArrayList<>();
        List<GiftCertificate> giftCertificateListAfterConvPurchase = new ArrayList<>();
        giftCertificateList.add(giftCertificate);
        giftCertificateListAfterConvPurchase.add(giftCertificateAfterConvPurchase);
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(1L, "test", "test", 10,
                BigDecimal.valueOf(10.2), null, null, StateCertificate.ACTIVE, tagDtoList);
        List<GiftCertificateDto> giftCertificateDtoList = new ArrayList<>();
        giftCertificateDtoList.add(giftCertificateDto);
        List<Long> giftCertificateListId = new ArrayList<>();
        giftCertificateListId.add(1L);
        PurchaseShortDto purchaseShortDto = new PurchaseShortDto(1L, 1L,
                giftCertificateListId);
        Purchase purchase= new Purchase(1L, user,
                BigDecimal.valueOf(10, 2), null, null, giftCertificateList);
        Purchase purchaseAfterConvPurchase= new Purchase(1L, userAfterConvPurchase,
                BigDecimal.valueOf(10, 2), null, null,  giftCertificateListAfterConvPurchase);
        Mockito.when(userRepository.findById(testId)).thenReturn(Optional.of(user));
        Mockito.when(giftCertificateRepository.findById(1L)).thenReturn(Optional.of(giftCertificate));
        PurchaseDto expected = new PurchaseDto(1L, userShortDto,
                BigDecimal.valueOf(10, 2), null, null, giftCertificateDtoList);
        Mockito.when(purchaseRepository.save(purchaseAfterConvPurchase)).thenReturn(purchase);
        Mockito.when(purchaseRepository.findById(1L)).thenReturn(Optional.of(purchase));
        assertEquals(expected, purchaseService.addPurchase(purchaseShortDto));
    }

    @Test
    void findEntityById() throws RecourseNotExistException {
        long testId = 1L;
        UseShortDto userShortDto = new UseShortDto(1L, "test", "test");
        User user = new User(1L, "test", "test", null);
        Purchase purchase = new Purchase(testId, user,
                BigDecimal.valueOf(10, 12), null, null, null);
        PurchaseDto expected = new PurchaseDto(testId, userShortDto,
                BigDecimal.valueOf(10, 12), null, null, null);
        Mockito.when(userRepository.findById(testId)).thenReturn(Optional.of(user));
        Mockito.when(purchaseRepository.findById(testId)).thenReturn(Optional.of(purchase));
        assertEquals(expected, purchaseService.findEntityById(testId));
    }

    @Test
    void findAll() {
        int pageN = 1;
        int pageSize = 2;
        long testId = 1L;
        UseShortDto userShortDto = new UseShortDto(1L, "test", "test");
        User user = new User(1L, "test", "test", null);
        PurchaseDto purchaseDto = new PurchaseDto(1L, userShortDto,
                BigDecimal.valueOf(10, 12), null, null, null);
        Purchase purchase = new Purchase(testId, user,
                BigDecimal.valueOf(10, 12), null, null, null);
        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(purchase);
        Page<Purchase> purchasePage = new PageImpl<>(purchaseList);
        Mockito.when(purchaseRepository.findAll(PageRequest.of(0, pageSize))).thenReturn(purchasePage);
        List<PurchaseDto> expected = new ArrayList<>();
        expected.add(purchaseDto);
        assertEquals(expected, purchaseService.findAll(pageN, pageSize));
    }

    @Test
    void findListPurchaseByUserId() throws RecourseNotExistException {
        long testId = 1L;
        long count = 1L;
        int pageN = 1;
        int pageSize = 1;
        UseShortDto userShortDto = new UseShortDto(1L, "test", "test");
        User user = new User(1L, "test", "test", null);
        PurchaseDto purchaseDto = new PurchaseDto(testId, userShortDto,
                BigDecimal.valueOf(10, 12), null, null, null);
        Purchase purchase = new Purchase(testId, user,
                BigDecimal.valueOf(10, 12), null, null, null);
        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(purchase);
        Mockito.when(purchaseRepository.countPurchaseByUser_UserId(testId)).thenReturn(count);
        Mockito.when(userRepository.findById(testId)).thenReturn(Optional.of(user));
        Mockito.when(purchaseRepository.findPurchasesByUser_UserId(testId, PageRequest.of(0, 1))).thenReturn(purchaseList);
        List<PurchaseDto> expected = new ArrayList<>();
        expected.add(purchaseDto);
        assertEquals(expected, purchaseService.findListPurchaseByUserId(testId, pageN, pageSize));
    }

    @Test
    void testAdd() {
        long testId = 1L;
        UseShortDto userShortDto = new UseShortDto(1L, "test", "test");
        PurchaseDto purchaseDto = new PurchaseDto(testId, userShortDto,
                BigDecimal.valueOf(10, 12), null, null, null);
        assertThrows(UnsupportedOperationException.class, () -> purchaseService.add(purchaseDto));
    }

    @Test
    void update() {
        long testId = 1L;
        UseShortDto userShortDto = new UseShortDto(1L, "test", "test");
        PurchaseDto purchaseDto = new PurchaseDto(testId, userShortDto,
                BigDecimal.valueOf(10, 12), null, null, null);
        assertThrows(UnsupportedOperationException.class, () -> purchaseService.update(purchaseDto));
    }

    @Test
    void delete() {
        long testId = 1L;
        assertThrows(UnsupportedOperationException.class, () -> purchaseService.delete(testId));
    }
}
