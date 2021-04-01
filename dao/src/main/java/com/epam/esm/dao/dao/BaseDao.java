package com.epam.esm.dao.dao;
import java.util.Optional;

public abstract class BaseDao<T> {

    /**
     * Finds entity by entity id
     *
     * @param id the entity id
     * @return the t
     */
    public abstract Optional<T> findEntityById(long id) ;

    /**
     * Deletes entity
     *
     * @param id the entity id
     * @return the boolean
     */
    public abstract boolean delete(long id) ;

    /**
     * Creates entity
     *
     * @param entity the entity
     * @return the int
     */
    public abstract int create(T entity) ;

    /**
     * Updates entity
     *
     * @param entity the entity
     * @return the int
     */
    public abstract int update(T entity);
}
