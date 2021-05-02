package com.epam.esm.service;

import com.epam.esm.model.dto.GiftCertificateDto;
import com.epam.esm.service.exeption.IllegalRequestParameterException;
import com.epam.esm.service.exeption.IllegalRequestSortParameterException;
import com.epam.esm.service.exeption.RecourseNotExistException;

import java.util.List;
import java.util.Map;

/**
 * The interface Certificate service.
 */
public interface CertificateService extends CommonService<GiftCertificateDto> {
    /**
     * Find certificatesby parameters
     *
     * @param groupParameters group parameters for search
     * @param offset
     * @param limit
     * @return list
     * @throws throws IllegalRequestParameterException
     * @throws throws IllegalRequestSortParameterException
     */
    List<GiftCertificateDto> findGiftCertificateListByParams(Map<String, List<String>> groupParameters, int offset, int limit) throws IllegalRequestParameterException, IllegalRequestSortParameterException;
}
