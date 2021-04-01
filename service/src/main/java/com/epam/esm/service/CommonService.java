package com.epam.esm.service;

public interface CommonService<T> {
    T add(T t);
    T update(T t);
    T get(long id);
    void delete(long id);
}
