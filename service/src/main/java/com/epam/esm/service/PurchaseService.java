package com.epam.esm.service;

import com.epam.esm.model.dto.PurchaseShortDto;
import com.epam.esm.model.dto.PurchaseDto;
import com.epam.esm.service.exeption.RecourseNotExistException;

import java.util.List;
/**
 * The interface Purchase service.
 */
public interface PurchaseService extends CommonService<PurchaseDto> {
    /**
     * Add purchase.
     *
     * @param purchaseCreateDto
     * @return PurchaseDto
     * @throws RecourseNotExistException
     */
    PurchaseDto addPurchase(PurchaseShortDto purchaseCreateDto) throws RecourseNotExistException;
    /**
     * Find purchases be user ID
     *
     * @param userId the user id
     * @param pageN the user id
     * @return list
     * @throws RecourseNotExistException
     */
    List<PurchaseDto> findListPurchaseByUserId(long userId,int pageN, int pageSize) throws RecourseNotExistException;
}
