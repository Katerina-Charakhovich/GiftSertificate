package com.epam.esm.dao;

import java.util.List;
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
     * @param entity the entity
     * @return void
     */
    void delete(T entity);

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
    T update(T entity);

    /**
     * Finds all entities limit size and page
     *
     * @param offset the number of page
     * @param limit limit count of entities
     * @return the int
     */
    List<T> findAll(int offset, int limit);
}
