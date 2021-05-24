package com.epam.esm.service.converter;

import com.epam.esm.dao.entity.User;
import com.epam.esm.model.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class CustomUserDetailsConverter {
    public CustomUserDetails convertTo(User entity) {
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setId(entity.getUserId());
        userDetails.setUserName(entity.getLogin());
        userDetails.setPassword(entity.getPassword());
        userDetails.setGrantedAuthorities(Collections.singletonList(new SimpleGrantedAuthority(entity.getRoleUser().name())));
        return userDetails;
    }
}
