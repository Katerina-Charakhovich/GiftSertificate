package com.epam.esm.service.impl;

import com.epam.esm.dao.UserRepository;
import com.epam.esm.model.dto.RoleUser;
import com.epam.esm.dao.entity.User;
import com.epam.esm.model.dto.UserDto;
import com.epam.esm.service.UserService;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceImplTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void findEntityById() throws RecourseNotExistException {
        long userId = 1;
        User user = new User(userId, "userName", "userSurName", "login", "password", RoleUser.ROLE_USER, null);
        UserDto userDto = new UserDto(userId, "userName", "userSurName", null);
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        assertEquals(userDto, userService.findEntityById(userId));
    }

    @Test
    void findEntityByIdTestNegative() {
        long id = 12;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class, () -> userService.findEntityById(id));
    }

    @Test
    void findAll() {
        int pageNo = 0;
        int pageSize = 2;
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<User> listUser = new ArrayList<>();
        Page<User> userPage = new PageImpl<>(listUser);
        Mockito.when(userRepository.findAll(paging)).thenReturn(userPage);
        List<UserDto> expected = new ArrayList<>();
        List<UserDto> actual = userService.findAll(pageNo, pageSize);
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        UserDto userDto = new UserDto(1L, "userName", "userSurName", null);
        assertThrows(UnsupportedOperationException.class, () -> userService.add(userDto));
    }

    @Test
    void update() {
        UserDto userDto = new UserDto(1L, "userName", "userSurName", null);
        assertThrows(UnsupportedOperationException.class, () -> userService.update(userDto));
    }

    @Test
    void delete() {
        long id = 1L;
        assertThrows(UnsupportedOperationException.class, () -> userService.delete(id));
    }
}
