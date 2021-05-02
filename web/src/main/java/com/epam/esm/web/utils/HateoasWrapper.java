package com.epam.esm.web.utils;

import com.epam.esm.model.dto.*;
import com.epam.esm.web.controller.GiftCertificateController;
import com.epam.esm.web.controller.PurchaseController;
import com.epam.esm.web.controller.TagController;
import com.epam.esm.web.controller.UserController;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class HateoasWrapper {
    private final String PURCHASES = "purchases";
    private final String CERTIFICATES = "certificates";
    private final String TAGS = "tags";

    public UserDto hateoasWrapperUserDto(UserDto userDto) {
        userDto.add(linkTo(UserController.class).
                slash(userDto.getId()).withSelfRel());
        userDto.add(linkTo(UserController.class).slash(userDto.getId()).slash(PURCHASES).withRel(PURCHASES));
        return userDto;
    }
    public UseShortDto hateoasWrapperUserShortDto(UseShortDto userDto) {
        userDto.add(linkTo(UserController.class).
                slash(userDto.getId()).withSelfRel());
        userDto.add(linkTo(UserController.class).slash(userDto.getId()).slash(PURCHASES).withRel(PURCHASES));
        return userDto;
    }

    public List<UserDto> hateoasWrapperListUserDto(List<UserDto> listUserDto) {
        return listUserDto.stream().map(s -> hateoasWrapperUserDto(s)).collect(Collectors.toList());
    }

    public GiftCertificateDto hateoasWrapperGiftCertificateDto(GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.add(linkTo(GiftCertificateController.class).
                slash(giftCertificateDto.getId()).withSelfRel());
        hateoasWrapperListTagDto(giftCertificateDto.getListTagDto());
        return giftCertificateDto;
    }

    public List<GiftCertificateDto> hateoasWrapperListGiftCertificateDto(List<GiftCertificateDto> listGiftCertificateDto) {
        return listGiftCertificateDto.stream().map(s -> hateoasWrapperGiftCertificateDto(s)).collect(Collectors.toList());
    }

    public TagDto hateoasWrapperTagDto(TagDto tagDto) {
        return (TagDto) tagDto.add(linkTo(TagController.class).
                slash(tagDto.getTagId()).withSelfRel());
    }

    public List<TagDto> hateoasWrapperListTagDto(List<TagDto> listTagDto) {
        return listTagDto.stream().map(s -> hateoasWrapperTagDto(s)).collect(Collectors.toList());
    }

    public PurchaseDto hateoasWrapperPurchaseDto(PurchaseDto purchaseDto) {
        purchaseDto.add(linkTo(PurchaseController.class).
                slash(purchaseDto.getId()).withSelfRel());
        purchaseDto.setUserShortDto(hateoasWrapperUserShortDto(purchaseDto.getUserShortDto()));
        for (GiftCertificateDto giftCertificateDto : purchaseDto.getListGiftCertificateDto()
        ) {
            hateoasWrapperGiftCertificateDto(giftCertificateDto);
        }
        return purchaseDto;
    }

    public List<PurchaseDto> hateoasWrapperListPurchaseDto(List<PurchaseDto> listPurchaseDto) {
        return listPurchaseDto.stream().map(s -> hateoasWrapperPurchaseDto(s)).collect(Collectors.toList());
    }
}
