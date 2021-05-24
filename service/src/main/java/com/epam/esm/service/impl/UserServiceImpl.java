package com.epam.esm.service.impl;

import com.epam.esm.dao.UserRepository;
import com.epam.esm.dao.entity.User;
import com.epam.esm.model.CustomPage;
import com.epam.esm.model.dto.UserDto;
import com.epam.esm.model.dto.UserRegistrationDto;
import com.epam.esm.model.parameters.CustomErrorCode;
import com.epam.esm.service.UserService;
import com.epam.esm.service.converter.UserConverter;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto add(UserDto userDto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserDto update(UserDto userDto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserDto findEntityById(long id) throws RecourseNotExistException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new RecourseNotExistException(CustomErrorCode.RECOURSE_USER_EXIST, "User with id={" + id + "} doesn't exist"));
        return UserConverter.convertTo(user);
    }

    @Override
    public void delete(long id){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserDto> findAll(int pageNo, int pageSize) {
        CustomPage customPage = new CustomPage(pageNo, pageSize, userRepository.count());
        Page<User> listUser = userRepository.findAll(PageRequest.of(customPage.getPageNumber(), customPage.getPageSize()));
        return UserConverter.convertTo(listUser.getContent());
    }

    @Override
    public UserDto add(UserRegistrationDto userRegistrationDto) throws RecourseExistException {
        User user = userRepository.findByLogin(userRegistrationDto.getLogin()).orElseThrow(() ->
                new RecourseExistException(CustomErrorCode.RECOURSE_USER_EXIST, "User with login={" +
                        userRegistrationDto.getLogin() + "} exists"));
        User userToSave = UserConverter.convertRegistrationFrom(userRegistrationDto);
        userToSave.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        return UserConverter.convertTo(userRepository.save(userToSave));
    }
}
