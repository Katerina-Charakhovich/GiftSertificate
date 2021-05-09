package com.epam.esm.dao;

import com.epam.esm.model.dto.GiftCertificateDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface GiftCertificate dao.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
public interface GiftCertificateDao extends BaseDao<GiftCertificateDto> {
 /*   /**
     * Delete certificate link wifh tag
     *
     * @param id the certificate id
     * @return boolean
     */
  /*  boolean deleteLinkTagById(long id);*/

    /**
     * Find certificates wifh tags by parameters
     *
     * @param groupParams the group of parameters search and sort
     * @return list
     */
    List<GiftCertificateDto> findEntityByParams(Map<String, List<String>> groupParams, int offset,int limit);

    /**
     * Find certificate by name
     *
     * @param certificateName the certificate name
     * @return Optional
     */
    Optional<GiftCertificateDto> findByName(String certificateName);
}
