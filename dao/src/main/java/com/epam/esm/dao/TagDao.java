package com.epam.esm.dao;

import com.epam.esm.model.entity.TagDto;

import java.util.List;
import java.util.Optional;

/**
 * The interface tag dao.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */

public interface TagDao extends BaseDao<TagDto>{
    /**
     * Find Tag by name
     *
     * @param tagName Tag name
     * @return optional tag
     */
   Optional<TagDto> findByName(String tagName);

    /**
     * Find list tags by certificate id
     *
     * @param certificateId Certificate id
     * @return list
     */
     List<TagDto> findListByCertificateId(long certificateId);
}
