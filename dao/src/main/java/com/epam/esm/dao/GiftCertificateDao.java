package com.epam.esm.dao;

import com.epam.esm.model.entity.GiftCertificateDto;

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
    /**
     * Delete certificate link wifh tag
     *
     * @param id the certificate id
     * @return boolean
     */
    boolean deleteLinkTagById(long id);

    /**
     * Add certificate link wifh tag
     *
     * @param certificateId the certificate id
     * @param tagId         the tag id
     * @return boolean
     */
    boolean addLinkTag(long certificateId, long tagId);

    /**
     * Find certificates wifh tags by parameters
     *
     * @param groupParams the group of parameters search and sort
     * @return list
     */
    List<GiftCertificateDto> findEntityByParams(Map<String, String> groupParams);

    /**
     * Find certificate by name
     *
     * @param certificateName the certificate name
     * @return Optional
     */
    Optional<GiftCertificateDto> findByName(String certificateName);
}
