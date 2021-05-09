package com.epam.esm.service;

import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

/**
 * The interface Common service.
 */
interface CommonService<T> {
    /**
     * Add element type T.
     *
     * @param  t element type T
     * @return element type T
     */
    T add(T t) throws RecourseExistException, RecourseNotExistException;
    /**
     * Update element type T.
     *
     * @param  t element type T
     * @return element type T
     */
    T update(T t) throws RecourseNotExistException;
    /**
     * Update element type T.
     *
     * @param  id id of element type T.
     * @return element type T
     */
    T findEntityById(long id) throws RecourseNotExistException;
    /**
     * Delete element type T.
     *
     * @param  id id of element type T.
     * @return void
     */
    void delete(long id) throws RecourseNotExistException;

    /**
     * Find all elements type T.
     *
     * @param offset
     * @param limit
     * @return the list elements of type T
     */
    List<T> findAll(int offset, int limit);
}
