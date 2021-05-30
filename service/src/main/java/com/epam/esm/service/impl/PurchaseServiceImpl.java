package com.epam.esm.service.impl;

import com.epam.esm.dao.*;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.entity.Purchase;
import com.epam.esm.dao.entity.User;
import com.epam.esm.model.CustomPage;
import com.epam.esm.model.dto.*;
import com.epam.esm.model.parameters.CustomErrorCode;
import com.epam.esm.service.PurchaseService;
import com.epam.esm.service.converter.PurchaseConverter;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.epam.esm.model.parameters.CustomErrorCode.RECOURSE_PURCHASE_NOT_EXIST;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final GiftCertificateRepository giftCertificateRepository;

    @Autowired
    public PurchaseServiceImpl(UserRepository userRepository, PurchaseRepository purchaseRepository, GiftCertificateRepository giftCertificateRepository) {
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
        this.giftCertificateRepository = giftCertificateRepository;
    }

    @Override
    public PurchaseDto add(PurchaseDto purchaseDto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PurchaseDto update(PurchaseDto purchaseDto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PurchaseDto findEntityById(long id) throws RecourseNotExistException {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() ->
                        new RecourseNotExistException(RECOURSE_PURCHASE_NOT_EXIST, "Purchase with id={" + id + "} doesn't exist"));
        return PurchaseConverter.convertTo(purchase);
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PurchaseDto> findAll(int pageNo, int pageSize) {
        CustomPage customPage = new CustomPage(pageNo, pageSize, purchaseRepository.count());
        List<Purchase> listPurchase = purchaseRepository.findAll(PageRequest.of(customPage.getPageNumber(), customPage.getPageSize())).toList();
        return PurchaseConverter.convertTo(listPurchase);
    }

    @Override
    @Transactional
    public PurchaseDto addPurchase(PurchaseShortDto purchaseCreateDto) throws RecourseNotExistException {
        User user = userRepository.findById(purchaseCreateDto.getUserId()).orElseThrow(() ->
                new RecourseNotExistException(CustomErrorCode.RECOURSE_USER_NOT_EXIST, "User={"
                        + purchaseCreateDto.getUserId() + "} doesn't exist"));
        long purchaseCost = 0;
        for (long giftCertificateId : purchaseCreateDto.getListGiftCertificate()
        ) {
            GiftCertificate giftCertificate = giftCertificateRepository.findById(giftCertificateId).orElseThrow(() ->
                    new RecourseNotExistException(CustomErrorCode.RECOURSE_CERTIFICATE_NOT_EXIST, "Certificate={"
                            + giftCertificateId + "} doesn't exist"));
            if (giftCertificate.getState()==StateCertificate.DELETED){
                throw  new RecourseNotExistException(CustomErrorCode.RECOURSE_CERTIFICATE_NOT_EXIST, "Certificate={"
                        + giftCertificateId + "} is deleted");
            }
            purchaseCost += purchaseCost + giftCertificate.getPrice().longValue();
        }
        Purchase purchase = PurchaseConverter.convertShortFrom(purchaseCreateDto);
        purchase.setPrice(BigDecimal.valueOf(purchaseCost));
        Purchase newPurchase = purchaseRepository.save(purchase);
        return findEntityById(purchase.getPurchaseId());
    }

    @Override
    public List<PurchaseDto> findListPurchaseByUserId(long userId, int pageNo, int pageSize) throws RecourseNotExistException {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RecourseNotExistException(CustomErrorCode.RECOURSE_USER_NOT_EXIST, "User={"
                        + userId + "} doesn't exist"));
        CustomPage customPage = new CustomPage(pageNo, pageSize, purchaseRepository.countPurchaseByUser_UserId(userId));
        List<Purchase> page = purchaseRepository.findPurchasesByUser_UserId(userId, PageRequest.of(customPage.getPageNumber(), customPage.getPageSize()));
        return PurchaseConverter.convertTo(page);
    }
}
