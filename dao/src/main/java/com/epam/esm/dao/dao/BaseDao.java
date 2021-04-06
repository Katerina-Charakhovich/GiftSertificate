package com.epam.esm.dao.dao;

import java.util.Optional;

/**
 * The interface base dao.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
public interface BaseDao<T> {

    /**
     * Finds entity by entity id
     *
     * @param id the entity id
     * @return the t
     */
    Optional<T> findEntityById(long id);

    /**
     * Deletes entity
     *
     * @param id the entity id
     * @return the boolean
     */
    boolean delete(long id);

    /**
     * Creates entity
     *
     * @param entity the entity
     * @return the int
     */
     T create(T entity);

    /**
     * Updates entity
     *
     * @param entity the entity
     * @return the int
     */
     int update(T entity);
}
