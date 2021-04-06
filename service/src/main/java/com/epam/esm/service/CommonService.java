package com.epam.esm.service;

import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;

public interface CommonService<T> {
    T add(T t) throws RecourseExistException;
    T update(T t);
    T findEntityById(long id) throws RecourseNotExistException;
    boolean delete(long id) throws RecourseNotExistException;
}
