package com.epam.esm.service;

import com.epam.esm.model.dto.PurchaseShortDto;
import com.epam.esm.model.dto.PurchaseDto;
import com.epam.esm.service.exeption.RecourseNotExistException;

import java.util.List;

public interface PurchaseService extends CommonService<PurchaseDto> {
    PurchaseDto addPurchase(PurchaseShortDto purchaseCreateDto) throws RecourseNotExistException;
    List<PurchaseDto> findListPurchaseByUserId(long userId) throws RecourseNotExistException;
}
