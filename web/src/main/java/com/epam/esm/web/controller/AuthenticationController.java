package com.epam.esm.web.controller;

import com.epam.esm.dao.UserRepository;
import com.epam.esm.dao.entity.Role;
import com.epam.esm.model.dto.UserAuthDto;
import com.epam.esm.model.dto.UserRegistrationDto;
import com.epam.esm.service.CustomUserDetailsService;
import com.epam.esm.service.UserService;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.web.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@Validated
public class AuthenticationController {
    private static final String USER_NAME = "username";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    /**
     * Authentication endpoint.
     *
     * @param userAuthDto the request object, containing auth data
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody UserAuthDto userAuthDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthDto.getLogin(),
                userAuthDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        return getTokenResponseEntity(userAuthDto.getLogin(),roles);
    }

    /**
     * Endpoint for registration new users.
     *
     * @param userRegistrationDto the user registration data
     * @return the user dto
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) throws RecourseExistException {
        userService.add(userRegistrationDto);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        return getTokenResponseEntity(userRegistrationDto.getLogin(), roles);
    }

    /**
     * Method takes username, converts it to token,
     * puts username and token into map and returns its response entity
     *
     * @param login the user name
     * @return the response entity of map
     */
    private ResponseEntity<Map<String, String>> getTokenResponseEntity(String login, Set<Role> roles) {
        String token = jwtTokenProvider.createToken(login, roles);
        Map<String, String> response = new HashMap<>();
        response.put(USER_NAME, login);
        response.put(ACCESS_TOKEN, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Logout endpoint.
     *
     * @param request  the request
     * @param response the response
     */
    @DeleteMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.NO_CONTENT.value());
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
    }
}
