package com.epam.esm.dao.converter.impl;

import com.epam.esm.dao.converter.Converter;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.model.entity.TagDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagConverter implements Converter<Tag, TagDto> {
    @Override
    public TagDto convertTo(Tag entity) {
        TagDto tagDto = new TagDto();
        tagDto.setTagId(entity.getTagId());
        tagDto.setTagName(entity.getTagName());
        return tagDto;
    }

    @Override
    public Tag convertFrom(TagDto entity) {
        Tag tag = new Tag();
        tag.setTagName(entity.getTagName());
        if (entity.getTagId() != 0) {
            tag.setTagId(entity.getTagId());
        }
        return tag;
    }

    @Override
    public List<TagDto> convertTo(List<Tag> entities) {
        List<TagDto> tagsDto = new ArrayList<>();
        entities.forEach(t -> tagsDto.add(convertTo(t)));
        return tagsDto;
    }


}
