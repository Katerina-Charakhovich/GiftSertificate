package com.epam.esm.dao.dao;

import com.epam.esm.model.entity.Tag;

import java.util.List;
import java.util.Optional;

/**
 * The interface tag dao.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */

public interface TagDao extends BaseDao<Tag>{
    /**
     * Find Tag by name
     *
     * @param tagName Tag name
     * @return optional tag
     */
   Optional<Tag> findByName(String tagName);

    /**
     * Find list tags by certificate id
     *
     * @param certificateId Certificate id
     * @return list
     */
     List<Tag> findListByCertificateId(long certificateId);
}
