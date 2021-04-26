package com.epam.esm.dao.converter;

import com.epam.esm.dao.entity.Tag;
import com.epam.esm.model.dto.TagDto;

import java.util.List;
import java.util.stream.Collectors;

public class TagConverter {
    public static TagDto convertTo(Tag entity) {
        TagDto tagDto = new TagDto();
        tagDto.setTagId(entity.getTagId());
        tagDto.setTagName(entity.getTagName());
        return tagDto;
    }

    public static Tag convertFrom(TagDto entity) {
        Tag tag = new Tag();
        tag.setTagName(entity.getTagName());
        if (entity.getTagId() != 0) {
            tag.setTagId(entity.getTagId());
        }
        return tag;
    }

    public static List<TagDto> convertTo(List<Tag> entities) {
        return entities.stream().map(TagConverter::convertTo).collect(Collectors.toList());
    }

    public static List<Tag> convertFrom(List<TagDto> entities) {
        return entities.stream().map(TagConverter::convertFrom).collect(Collectors.toList());
    }
}
