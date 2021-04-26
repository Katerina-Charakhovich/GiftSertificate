package com.epam.esm.service;

import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

public interface CommonService<T> {
    T add(T t) throws RecourseExistException, RecourseNotExistException;

    T update(T t) throws RecourseNotExistException;

    T findEntityById(long id) throws RecourseNotExistException;

    Executable delete(long id) throws RecourseNotExistException;

    List<T> findAll(int offset, int limit);
}
