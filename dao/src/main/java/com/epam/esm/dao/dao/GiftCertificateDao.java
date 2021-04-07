package com.epam.esm.dao.dao;


import com.epam.esm.model.entity.GiftCertificate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface GiftCertificate dao.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
public interface GiftCertificateDao extends BaseDao<GiftCertificate>{
    boolean deleteLinkTagById(long id);
    boolean addLinkTag(long certificateId,long tagId);
    List<GiftCertificate> findEntityByParams (Map<String,String> groupParams );

}
