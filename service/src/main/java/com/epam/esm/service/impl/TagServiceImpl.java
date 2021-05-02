package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.parameters.CustomErrorCode;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
            throw new RecourseExistException(CustomErrorCode.RECOURSE_TAG_EXIST, "Tag with name={" + tagDto.getTagName() + "} exists");
        return tagDao.create(tagDto);
    }

    @Override
    public TagDto update(TagDto tagDto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(long id) throws RecourseNotExistException {
        Optional<TagDto> optionalTag = tagDao.findEntityById(id);
        if (!optionalTag.isPresent())
            throw new RecourseNotExistException(CustomErrorCode.RECOURSE_TAG_NOT_EXIST, "Tag with id={" + id + "} doesn't exist");
        tagDao.delete(optionalTag.get());
    }

    @Override
    public List<TagDto> findAll(int offset, int limit) {
        return tagDao.findAll(offset,limit);
    }

    @Override
    public TagDto findEntityById(long id) throws RecourseNotExistException {
        Optional<TagDto> optionalTag = tagDao.findEntityById(id);
        if (!optionalTag.isPresent())
            throw new RecourseNotExistException(CustomErrorCode.RECOURSE_TAG_NOT_EXIST, "Tag with id={" + id + "} doesn't exist");
        return optionalTag.get();
    }

    @Override
    public Optional<TagDto> findPopularTag() {
        return tagDao.findPopularTag();
    }
}
