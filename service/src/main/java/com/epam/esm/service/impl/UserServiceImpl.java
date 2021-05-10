package com.epam.esm.service.impl;

import com.epam.esm.dao.UserRepository;
import com.epam.esm.dao.entity.User;
import com.epam.esm.model.dto.UserDto;
import com.epam.esm.model.dto.UserRegistrationDto;
import com.epam.esm.model.parameters.CustomErrorCode;
import com.epam.esm.service.UserService;
import com.epam.esm.service.converter.UserConverter;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto add(UserDto userDto) throws RecourseExistException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserDto update(UserDto userDto) throws RecourseNotExistException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserDto findEntityById(long id) throws RecourseNotExistException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            throw new RecourseNotExistException(CustomErrorCode.RECOURSE_USER_EXIST, "User with id={" + id + "} doesn't exist");
        return UserConverter.convertTo(optionalUser.get());
    }

    @Override
    public void delete(long id) throws RecourseNotExistException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserDto> findAll(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<User> listUser = userRepository.findAll(paging).toList();
        return UserConverter.convertTo(listUser);
    }

    @Override
    public UserDto add(UserRegistrationDto userRegistrationDto) throws RecourseExistException {
        Optional<User> optionalUser = userRepository.findByLogin(userRegistrationDto.getLogin());
        if (optionalUser.isPresent())
            throw new RecourseExistException(CustomErrorCode.RECOURSE_USER_EXIST, "User with login={" +
                    userRegistrationDto.getLogin() + "} exists");
        User newUser = userRepository.save(UserConverter.convertRegistrationFrom(userRegistrationDto));
        return UserConverter.convertTo(newUser);
    }
}
