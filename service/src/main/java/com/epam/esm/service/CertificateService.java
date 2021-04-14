package com.epam.esm.service;

import com.epam.esm.model.entity.GiftCertificateDto;
import com.epam.esm.service.exeption.IllegalRequestParameterException;
import com.epam.esm.service.exeption.IllegalRequestSortParameterException;

import java.util.List;
import java.util.Map;

public interface CertificateService extends CommonService<GiftCertificateDto> {
    List<GiftCertificateDto> findGiftCertificateListByParams(Map<String, String> groupParameters) throws IllegalRequestParameterException, IllegalRequestSortParameterException;
}
