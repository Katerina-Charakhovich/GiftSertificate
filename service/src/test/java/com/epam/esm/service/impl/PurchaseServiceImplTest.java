package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.PurchaseDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.impl.PurchaseDaoImpl;
import com.epam.esm.dao.impl.UserDaoImpl;
import com.epam.esm.model.dto.*;
import com.epam.esm.service.PurchaseService;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseServiceImplTest {
    @InjectMocks
    private PurchaseService purchaseService;
    @Mock
    PurchaseDao purchaseDao;
    @Mock
    UserDao userDao;
    @Mock
    GiftCertificateDao giftCertificateDao;

   @BeforeEach
    public void setUp() {
        purchaseDao = Mockito.mock(PurchaseDaoImpl.class);
        userDao = Mockito.mock(UserDaoImpl.class);
        giftCertificateDao = Mockito.mock(GiftCertificateDao.class);
        userDao = Mockito.mock(UserDaoImpl.class);
        purchaseService = new PurchaseServiceImpl(userDao, purchaseDao, giftCertificateDao);
    }

    @Test
    void add() throws RecourseNotExistException {
        long testId = 1L;
        UseShortDto userShortDto = new UseShortDto(1L, "test", "test");
        UserDto userDto = new UserDto(1L, "test", "test",null);
        TagDto tagDto = new TagDto(1L, "testTag");
        List<TagDto> tagDtoList = new ArrayList<>();
        tagDtoList.add(tagDto);
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(1L, "test", "test", 10,
                BigDecimal.valueOf(10.2), null, null, tagDtoList);
        List<GiftCertificateDto> giftCertificateDtoList = new ArrayList<>();
        giftCertificateDtoList.add(giftCertificateDto);
        List<Long> giftCertificateListId = new ArrayList<Long>();
        giftCertificateListId.add(1L);
        PurchaseShortDto purchaseShortDto = new PurchaseShortDto(1L, 1L, BigDecimal.valueOf(10, 12),
                giftCertificateListId);
        PurchaseDto purchaseDto = new PurchaseDto(1L, userShortDto,
                BigDecimal.valueOf(10, 12), null, null, giftCertificateDtoList);
        Mockito.when(userDao.findEntityById(testId)).thenReturn(Optional.of(userDto));
        Mockito.when(giftCertificateDao.findEntityById(testId)).thenReturn(Optional.of(giftCertificateDto));

        PurchaseDto expected = new PurchaseDto(1L, userShortDto,
                BigDecimal.valueOf(10, 12), null, null, giftCertificateDtoList);

        Mockito.when(purchaseDao.addPurchaseDto(purchaseShortDto)).thenReturn(purchaseShortDto);
        Mockito.when(purchaseDao.findEntityById(1L)).thenReturn(Optional.of(purchaseDto));
        assertEquals(expected, purchaseService.addPurchase(purchaseShortDto));
    }

    @Test
    void findEntityById() throws RecourseNotExistException {
        long testId = 1L;
        UseShortDto userShortDto = new UseShortDto(1L, "test", "test");
        UserDto userDto = new UserDto(1L, "test", "test", null);
        PurchaseDto purchaseDto = new PurchaseDto(testId, userShortDto,
                BigDecimal.valueOf(10, 12), null, null, null);
        PurchaseDto expected = new PurchaseDto(testId, userShortDto,
                BigDecimal.valueOf(10, 12), null, null, null);
        Mockito.when(userDao.findEntityById(testId)).thenReturn(Optional.of(userDto));
        Mockito.when(purchaseDao.findEntityById(testId)).thenReturn(Optional.of(purchaseDto));
        assertEquals(expected, purchaseService.findEntityById(testId));
    }

    @Test
    void findAll() {
        int offset = 0;
        int limit = 2;
        UseShortDto userShortDto = new UseShortDto(1L, "test", "test");
        PurchaseDto purchaseDto = new PurchaseDto(1L, userShortDto,
                BigDecimal.valueOf(10, 12), null, null, null);
        List<PurchaseDto> purchaseDtoList = new ArrayList<>();
        purchaseDtoList.add(purchaseDto);
        Mockito.when(purchaseDao.findAll(offset, limit)).thenReturn(purchaseDtoList);
        List<PurchaseDto> expected = new ArrayList<>();
        expected.add(purchaseDto);
        assertEquals(expected, purchaseService.findAll(offset, limit));
    }

    @Test
    void findListPurchaseByUserId() throws RecourseNotExistException {
        long testId = 1L;
        UseShortDto userShortDto = new UseShortDto(1L, "test", "test");
        UserDto userDto = new UserDto(1L, "test", "test", null);
        PurchaseDto purchaseDto = new PurchaseDto(testId, userShortDto,
                BigDecimal.valueOf(10, 12), null, null, null);
        List<PurchaseDto> purchaseDtoList = new ArrayList<>();
        purchaseDtoList.add(purchaseDto);
        Mockito.when(userDao.findEntityById(testId)).thenReturn(Optional.of(userDto));
        Mockito.when(purchaseDao.findByUserId(testId)).thenReturn(purchaseDtoList);
        List<PurchaseDto> expected = new ArrayList<>();
        expected.add(purchaseDto);
        assertEquals(expected, purchaseService.findListPurchaseByUserId(testId));
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
