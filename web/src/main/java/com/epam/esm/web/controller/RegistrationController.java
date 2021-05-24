package com.epam.esm.web.controller;

import com.epam.esm.model.dto.UserDto;
import com.epam.esm.model.dto.UserRegistrationDto;
import com.epam.esm.service.UserService;
import com.epam.esm.service.exeption.RecourseExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

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
        UserDto userDto=userService.add(userRegistrationDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
