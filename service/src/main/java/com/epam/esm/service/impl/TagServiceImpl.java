package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.model.entity.TagDto;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    @Transactional
    public TagDto add(TagDto tagDto) throws RecourseExistException {
        Optional<TagDto> optionalTag = tagDao.findByName(tagDto.getTagName());
        if (optionalTag.isPresent())
            throw new RecourseExistException("Tag with name={" + tagDto.getTagName() + "} exists");
        return tagDao.create(tagDto);
    }

    @Override
    public TagDto update(TagDto tagDto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(long id) throws RecourseNotExistException {
        Optional<TagDto> optionalTag = tagDao.findEntityById(id);
        if (!optionalTag.isPresent())
            throw new RecourseNotExistException("Tag with id={" + id + " d} doesn't exist");
        return tagDao.delete(id);
    }

    @Override
    public TagDto findEntityById(long id) throws RecourseNotExistException {
        Optional<TagDto> optionalTag = tagDao.findEntityById(id);
        if (!optionalTag.isPresent())
            throw new RecourseNotExistException("Tag with id={" + id + " d} doesn't exist");
        return optionalTag.get();
    }
}
