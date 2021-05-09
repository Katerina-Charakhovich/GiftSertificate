package com.epam.esm.dao;

import com.epam.esm.model.dto.PurchaseShortDto;
import com.epam.esm.model.dto.PurchaseDto;

import java.util.List;

public interface PurchaseDao extends BaseDao<PurchaseDto> {
    PurchaseShortDto  addPurchaseDto(PurchaseShortDto purchaseShortDto);
    List<PurchaseDto> findByUserId(long id);
}
