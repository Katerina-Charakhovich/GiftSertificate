package com.epam.esm.service.impl;

import com.epam.esm.dao.dao.TagDao;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.CommonService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TagServiceImpl implements CommonService<Tag>, TagService {
    private final TagDao tagDao;


    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    @Transactional
    public Tag add(Tag tag) throws RecourseExistException {
        Optional<Tag> optionalTag = tagDao.findByName(tag.getTagName());
        if (optionalTag.isPresent())
            throw new RecourseExistException("Tag with name={" + tag.getTagName() + "} exists");
        return tagDao.create(tag);
    }

    @Override
    public Tag update(Tag tag) {
        return null;
    }


    @Override
    public boolean delete(long id) throws RecourseNotExistException {
        Optional<Tag> optionalTag = tagDao.findEntityById(id);
        if (!optionalTag.isPresent())
            throw new RecourseNotExistException("Tag with id={" + id + " d} doesn't exist");
        return tagDao.delete(id);
    }

    @Override
    public Tag findEntityById(long id) throws RecourseNotExistException {
        Optional<Tag> optionalTag = tagDao.findEntityById(id);
        if (!optionalTag.isPresent())
            throw new RecourseNotExistException("Tag with id={" + id + " d} doesn't exist");
        return optionalTag.get();
    }
}
