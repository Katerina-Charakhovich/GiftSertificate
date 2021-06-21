package com.epam.esm.web.controller;

import com.epam.esm.model.dto.UserDto;
import com.epam.esm.model.dto.UserRegistrationDto;
import com.epam.esm.service.UserService;
import com.epam.esm.service.exeption.RecourseExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
/**
 * The  Registration Controller
 * It used for Rest Api
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@Validated
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for registration new users.
     *
     * @param userRegistrationDto the user registration data
     * @return the user dto
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) throws RecourseExistException {
        UserDto userDto = userService.add(userRegistrationDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
