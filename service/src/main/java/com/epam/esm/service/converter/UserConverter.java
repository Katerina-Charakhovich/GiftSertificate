package com.epam.esm.service.converter;

import com.epam.esm.dao.entity.Purchase;
import com.epam.esm.dao.entity.Role;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.entity.User;
import com.epam.esm.model.dto.*;
import com.epam.esm.service.utils.Encryptor;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {
    public static UserDto convertTo(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getUserId());
        userDto.setName(entity.getUserName());
        userDto.setSurname(entity.getUserSurname());
        List<PurchaseShortDto> listPurchaseDto = null;
        if (entity.getListPurchase() != null) {
            listPurchaseDto = PurchaseConverter.convertShortTo(entity.getListPurchase());
        }
        userDto.setListPurchase(listPurchaseDto);
        return userDto;
    }

    public static UseShortDto convertShortTo(User entity) {
        UseShortDto userDto = new UseShortDto();
        userDto.setId(entity.getUserId());
        userDto.setName(entity.getUserName());
        userDto.setSurname(entity.getUserSurname());
        return userDto;
    }
    public static User convertRegistrationFrom(UserRegistrationDto entity) {
        User user = new User();
        user.setLogin(entity.getLogin());
        user.setUserSurname(entity.getSurname());
        user.setUserName(entity.getName());
        user.setPassword(Encryptor.hashPassword(entity.getPassword()));
        user.setRole(Role.ROLE_USER);
        return user;
    }

    public static User convertShortFrom(UseShortDto entity) {
        User user = new User();
        if (entity.getId() != null) {
            user.setUserId(entity.getId());
        }
        user.setUserName(entity.getName());
        user.setUserSurname(entity.getSurname());
        return user;
    }

    public static User convertFrom(UserDto entity) {
        User user = new User();
        if (entity.getId() != null) {
            user.setUserId(entity.getId());
        }
        user.setUserName(entity.getName());
        user.setUserSurname(entity.getSurname());
        List<Purchase> listPurchase = null;
        if (entity.getListPurchase() != null) {
            listPurchase = PurchaseConverter.convertShortFrom(entity.getListPurchase());
        }
        user.setListPurchase(listPurchase);
        return user;
    }

    public static List<UserDto> convertTo(List<User> entities) {
        return entities.stream().map(UserConverter::convertTo).collect(Collectors.toList());
    }

    public static List<Tag> convertFrom(List<TagDto> entities) {
        return entities.stream().map(TagConverter::convertFrom).collect(Collectors.toList());
    }
}
