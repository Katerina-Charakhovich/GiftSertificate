package com.epam.esm.service;

import com.epam.esm.model.dto.UserDto;
import com.epam.esm.model.dto.UserRegistrationDto;
import com.epam.esm.service.exeption.RecourseExistException;

/**
 * The interface User service.
 */
public interface UserService extends CommonService<UserDto> {
    UserDto add(UserRegistrationDto userRegistrationDto) throws RecourseExistException;
}
