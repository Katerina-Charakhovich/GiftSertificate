package com.epam.esm.dao.dao;


import com.epam.esm.model.entity.GiftCertificate;

/**
 * The interface GiftCertificate dao.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
public interface GiftCertificateDao extends BaseDao<GiftCertificate>{
    boolean deleteLinkTagById(long id);

}
