package com.epam.esm.service.converter;

import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.entity.Purchase;
import com.epam.esm.dao.entity.User;
import com.epam.esm.model.dto.GiftCertificateDto;
import com.epam.esm.model.dto.PurchaseDto;
import com.epam.esm.model.dto.PurchaseShortDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseConverter {
    public static PurchaseDto convertTo(Purchase entity) {
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setId(entity.getPurchaseId());
        purchaseDto.setUserShortDto(UserConverter.convertShortTo(entity.getUser()));
        purchaseDto.setPrice(entity.getPrice());
        purchaseDto.setCreateDate(entity.getCreateDate());
        purchaseDto.setLastUpdateDate(entity.getLastUpdateTime());
        List<GiftCertificateDto> listGiftCertificateDto = null;
        if (entity.getListGiftCertificate() != null) {
            listGiftCertificateDto = GiftCertificateConverter.convertTo(entity.getListGiftCertificate());
        }
        purchaseDto.setListGiftCertificateDto(listGiftCertificateDto);
        return purchaseDto;
    }

    public static PurchaseShortDto convertShortTo(Purchase entity) {
        PurchaseShortDto purchaseDto = new PurchaseShortDto();
        purchaseDto.setId(entity.getPurchaseId());
        purchaseDto.setUserId(entity.getUser().getUserId());
        List<Long> listGiftCertificateId = entity.getListGiftCertificate().stream().map(GiftCertificate::getId).collect(Collectors.toList());
        purchaseDto.setListGiftCertificate(listGiftCertificateId);
        return purchaseDto;
    }

    public static Purchase convertShortFrom(PurchaseShortDto entity) {
        Purchase purchase = new Purchase();
        if (entity.getId() != null) {
            purchase.setPurchaseId(entity.getId());
        }
        User user = new User();
        user.setUserId(entity.getUserId());
        purchase.setUser(user);
        List<GiftCertificate> listGiftCertificate = new ArrayList<>();
        for (Long id : entity.getListGiftCertificate()
        ) {
            GiftCertificate giftCertificate = new GiftCertificate();
            giftCertificate.setId(id);
            listGiftCertificate.add(giftCertificate);
        }
        purchase.setListGiftCertificate(listGiftCertificate);
        return purchase;
    }

    public static Purchase convertFrom(PurchaseDto entity) {
        Purchase order = new Purchase();
        if (entity.getId() != null) {
            order.setPurchaseId(entity.getId());
        }
        order.setUser(UserConverter.convertShortFrom(entity.getUserShortDto()));
        User user = new User();
        user.setUserId(entity.getUserShortDto().getId());
        order.setUser(user);
        order.setPrice(entity.getPrice());
        order.setCreateDate(entity.getCreateDate());
        order.setLastUpdateTime(entity.getLastUpdateDate());
        List<GiftCertificate> listGiftCertificate = null;
        if (entity.getListGiftCertificateDto() != null) {
            listGiftCertificate = GiftCertificateConverter.convertFrom(entity.getListGiftCertificateDto());
        }
        order.setListGiftCertificate(listGiftCertificate);
        return order;
    }

    public static List<PurchaseDto> convertTo(List<Purchase> entities) {
        return entities.stream().map(PurchaseConverter::convertTo).collect(Collectors.toList());
    }

    public static List<Purchase> convertFrom(List<PurchaseDto> entities) {
        return entities.stream().map(PurchaseConverter::convertFrom).collect(Collectors.toList());
    }
    public static List<PurchaseShortDto> convertShortTo(List<Purchase> entities) {
        return entities.stream().map(PurchaseConverter::convertShortTo).collect(Collectors.toList());
    }

    public static List<Purchase> convertShortFrom(List<PurchaseShortDto> entities) {
        return entities.stream().map(PurchaseConverter::convertShortFrom).collect(Collectors.toList());
    }
}
