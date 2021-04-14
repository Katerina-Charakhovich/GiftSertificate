package com.epam.esm.dao.converter;

import java.util.List;

public interface Converter<S, T> {
    T convertTo(S entity);

    S convertFrom(T entity);

    List<T> convertTo(List<S> entities);
}
