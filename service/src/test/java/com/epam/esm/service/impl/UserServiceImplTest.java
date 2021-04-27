package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.impl.UserDaoImpl;
import com.epam.esm.model.dto.UserDto;
import com.epam.esm.service.UserService;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

class UserServiceImplTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        userDao = Mockito.mock(UserDaoImpl.class);
        userService = new UserServiceImpl(userDao);
    }

    @Test
    void findEntityById() throws RecourseNotExistException {
        long userId = 1;
        UserDto userDto = new UserDto(userId,"userName","userSurName",null);
        Mockito.when(userDao.findEntityById(userId)).thenReturn(Optional.of(userDto));
        UserDto userDto1 = userService.findEntityById(userId);
        assertEquals(userDto, userService.findEntityById(userId));
    }
    @Test
    void findEntityByIdTestNegative() {
        long id = 12;
        Mockito.when(userDao.findEntityById(id)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class,()-> userService.findEntityById(id));
    }
    @Test
    void findAll() {
        int offset=0;
        int limit=2;
        List<UserDto> users = new ArrayList<>();
        Mockito.when(userDao.findAll(anyInt(), anyInt())).thenReturn(users);
        List<UserDto> expected = new ArrayList<>();
        List<UserDto> actual = userService.findAll(offset,limit);
        assertEquals(expected, actual);
    }
}
