package com.epam.esm.dao;

import com.epam.esm.model.dto.TagDto;

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
     * Find most popular tag
     *
     * @return optional tag
     */
    Optional<TagDto> findPopularTag();
}
