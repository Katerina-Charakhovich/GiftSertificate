package com.epam.esm.service.impl;

import com.epam.esm.dao.TagRepository;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.model.CustomPage;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.parameters.CustomErrorCode;
import com.epam.esm.service.TagService;
import com.epam.esm.service.converter.TagConverter;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public TagDto add(TagDto tagDto) throws RecourseExistException {
        Optional<Tag> optionalTag = tagRepository.findByTagName(tagDto.getTagName());
        if (optionalTag.isPresent())
            throw new RecourseExistException(CustomErrorCode.RECOURSE_TAG_EXIST, "Tag with name={" + tagDto.getTagName() + "} exists");
        Tag tag = TagConverter.convertFrom(tagDto);
        return TagConverter.convertTo(tagRepository.save(tag));
    }

    @Override
    public TagDto update(TagDto tagDto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(long id) throws RecourseNotExistException {
        Tag tag = tagRepository.findById(id).orElseThrow(() ->
                new RecourseNotExistException(CustomErrorCode.RECOURSE_TAG_NOT_EXIST, "Tag with id={" + id + "} doesn't exist"));
        tagRepository.delete(tag);
    }

    @Override
    public List<TagDto> findAll(int pageNo, int pageSize) {
        CustomPage customPage = new CustomPage(pageNo, pageSize, tagRepository.count());
        List<Tag> listTag = tagRepository.findAll(PageRequest.of(customPage.getPageNumber(), customPage.getPageSize())).toList();
        return TagConverter.convertTo(listTag);
    }

    @Override
    public TagDto findEntityById(long id) throws RecourseNotExistException {
        Tag tag = tagRepository.findById(id).orElseThrow(() ->
                new RecourseNotExistException(CustomErrorCode.RECOURSE_TAG_NOT_EXIST, "Tag with id={" + id + "} doesn't exist"));
        return TagConverter.convertTo(tag);
    }

    @Override
    public Optional<TagDto> findPopularTag() {
        Optional<Tag> tag = tagRepository.findPopularTag();
        return tag.isPresent() ? Optional.of(TagConverter.convertTo(tag.get())) : Optional.empty();
    }
}
