package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.model.dto.UserDto;
import com.epam.esm.model.parameters.CustomErrorCode;
import com.epam.esm.service.UserService;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserDao userDao;

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
        Optional<UserDto> optionalUser = userDao.findEntityById(id);
        if (!optionalUser.isPresent())
            throw new RecourseNotExistException(CustomErrorCode.RECOURSE_USER_EXIST, "User with id={" + id + "} doesn't exist");
        return optionalUser.get();
    }

    @Override
    public Executable delete(long id) throws RecourseNotExistException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserDto> findAll(int offset, int limit) {
        return userDao.findAll(offset,limit);
    }

}
