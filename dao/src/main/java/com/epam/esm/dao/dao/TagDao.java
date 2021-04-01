package com.epam.esm.dao.dao;

import com.epam.esm.dao.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * The interface tag dao.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */

public interface TagDao {
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
     List<Tag> findByCertificateId(long certificateId);

}
