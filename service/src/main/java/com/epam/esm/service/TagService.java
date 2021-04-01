package com.epam.esm.service;

import com.epam.esm.dao.entity.Tag;

import java.util.Optional;

public interface TagService {
    Optional<Tag> findEntityById(long id) ;
}
