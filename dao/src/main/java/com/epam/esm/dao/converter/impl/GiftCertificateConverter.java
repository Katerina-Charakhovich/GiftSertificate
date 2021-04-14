package com.epam.esm.dao.converter.impl;

import com.epam.esm.dao.converter.Converter;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.model.entity.GiftCertificateDto;
import com.epam.esm.model.entity.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GiftCertificateConverter implements Converter<GiftCertificate, GiftCertificateDto> {

    private final TagConverter tagConverter;

    @Autowired
    public GiftCertificateConverter(TagConverter tagConverter) {
        this.tagConverter = tagConverter;
    }

    public GiftCertificateDto convertTo(GiftCertificate entity) {
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setId(entity.getId());
        giftCertificateDto.setName(entity.getName());
        giftCertificateDto.setDescription(entity.getDescription());
        giftCertificateDto.setPrice(entity.getPrice());
        giftCertificateDto.setDuration(entity.getDuration());
        giftCertificateDto.setLastUpdateDate(entity.getLastUpdateDate());
        giftCertificateDto.setCreateDate(entity.getCreateDate());
        List<TagDto> tags = null;
        if (entity.getListTag() != null) {
            tags = tagConverter.convertTo(entity.getListTag());
        }
        giftCertificateDto.setListTag(tags);
        return giftCertificateDto;
    }

    @Override
    public GiftCertificate convertFrom(GiftCertificateDto entity) {
        GiftCertificate giftCertificate = new GiftCertificate();
        if (entity.getId() != 0) {
            giftCertificate.setId(entity.getId());
        }
        giftCertificate.setName(entity.getName());
        giftCertificate.setDescription(entity.getDescription());
        giftCertificate.setPrice(entity.getPrice());
        giftCertificate.setDuration(entity.getDuration());
        giftCertificate.setLastUpdateDate(entity.getLastUpdateDate());
        giftCertificate.setCreateDate(entity.getCreateDate());
        return giftCertificate;
    }

    @Override
    public List<GiftCertificateDto> convertTo(List<GiftCertificate> entities) {
        return entities.stream().map(this::convertTo).collect(Collectors.toList());
    }
}
