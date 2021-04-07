package com.epam.esm.service;


import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.service.exeption.IllegalRequestParameters;
import com.epam.esm.service.exeption.IllegalRequestSortParameters;

import java.util.List;
import java.util.Map;

public interface CertificateService extends CommonService<GiftCertificate> {
    List<GiftCertificate> findGiftCertificateListByParams(Map<String, String> groupParameters) throws IllegalRequestParameters, IllegalRequestSortParameters;
}
