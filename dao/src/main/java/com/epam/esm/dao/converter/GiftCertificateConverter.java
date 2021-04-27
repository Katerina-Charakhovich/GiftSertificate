package com.epam.esm.dao.converter;

import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.model.dto.GiftCertificateDto;
import com.epam.esm.model.dto.TagDto;

import java.util.List;
import java.util.stream.Collectors;

public class GiftCertificateConverter {
    public static GiftCertificateDto convertTo(GiftCertificate entity) {
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
            tags = TagConverter.convertTo(entity.getListTag());
        }
        giftCertificateDto.setListTagDto(tags);
        return giftCertificateDto;
    }

    public static GiftCertificate convertFrom(GiftCertificateDto entity) {
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
        List<Tag> tags = null;
        if (entity.getListTagDto() != null) {
            tags = TagConverter.convertFrom(entity.getListTagDto());
        }
        giftCertificate.setListTag(tags);
        return giftCertificate;
    }

    public static List<GiftCertificateDto> convertTo(List<GiftCertificate> entities) {
        return entities.stream().map(GiftCertificateConverter::convertTo).collect(Collectors.toList());
    }

    public static List<GiftCertificate> convertFrom(List<GiftCertificateDto> entities) {
        return entities.stream().map(GiftCertificateConverter::convertFrom).collect(Collectors.toList());
    }
}
