package com.epam.esm.service.impl;

import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.dao.impl.TagDaoImpl;
import com.epam.esm.service.CommonService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements CommonService<Tag>, TagService {

    private final TagDaoImpl tagDao;

    @Autowired
    public TagServiceImpl(TagDaoImpl tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag add(Tag tag) {
        return null;
    }

    @Override
    public Tag update(Tag tag) {
        return null;
    }

    @Override
    public Tag get(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Optional<Tag> findEntityById(long id) {
        Optional<Tag> optionalTag = tagDao.findEntityById(id);
        return optionalTag;
    }
}
