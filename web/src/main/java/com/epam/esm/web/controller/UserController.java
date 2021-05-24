package com.epam.esm.web.controller;

import com.epam.esm.model.dto.*;
import com.epam.esm.service.PurchaseService;
import com.epam.esm.service.UserService;
import com.epam.esm.service.exeption.RecourseNotExistException;
import com.epam.esm.web.utils.HateoasWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * The  User controller with mapping "/users"
 * It used for Rest Api
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
@Validated
public class UserController {
    private static final String DEFAULT_PAGE_N = "0";
    private static final String DEFAULT_PAGE_SIZE = "5";
    private static final String PAGE_N = "pageN";
    private static final String PAGE_SIZE = "pageSize";
    private final UserService userService;
    private final PurchaseService purchaseService;
    private final HateoasWrapper hateoasWrapper;

    @Autowired
    public UserController(UserService userService, PurchaseService purchaseService, HateoasWrapper hateoasWrapper) {
        this.userService = userService;
        this.purchaseService = purchaseService;
        this.hateoasWrapper = hateoasWrapper;
    }

    /**
     * Find user by id
     *
     * @param id the id
     * @return tag
     * @throws RecourseNotExistException if tag isn't found
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable @Positive long id) throws RecourseNotExistException {
        UserDto userDto = userService.findEntityById(id);
        return new ResponseEntity(hateoasWrapper.hateoasWrapperUserDto(userDto), HttpStatus.OK);
    }

    /**
     * Find all users
     *
     * @return list
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(
            @Valid @RequestParam(required = false, value = PAGE_N, defaultValue = DEFAULT_PAGE_N)
                    int pageN,
            @Valid @RequestParam(required = false, value = PAGE_SIZE, defaultValue = DEFAULT_PAGE_SIZE)
                    int pageSize) {
        List<UserDto> list = userService.findAll(pageN, pageSize);
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperListUserDto(list), HttpStatus.OK);
    }

    /**
     * Find purchases by user id
     *
     * @return list
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<PurchaseDto>> findPurchaseByUserId(
            @PathVariable @Positive long id,
            @Valid @RequestParam(required = false, value = PAGE_N, defaultValue = DEFAULT_PAGE_N)
                    int pageN,
            @Valid @RequestParam(required = false, value = PAGE_SIZE, defaultValue = DEFAULT_PAGE_SIZE)
                    int pageSize) throws RecourseNotExistException {
        return new ResponseEntity(hateoasWrapper.hateoasWrapperListPurchaseDto
                (purchaseService.findListPurchaseByUserId(id, pageN, pageSize)), HttpStatus.OK);
    }

    /**
     * Create purchase for user
     *
     * @return PurchaseShortDto
     */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/{id}/purchases")
    public ResponseEntity<PurchaseShortDto> addPurchaseByUserId(@Valid @RequestBody PurchaseShortDto purchaseCreateDto,
                                                                @PathVariable @Positive long id) throws RecourseNotExistException {
        purchaseCreateDto.setUserId(id);
        return new ResponseEntity(purchaseService.addPurchase(purchaseCreateDto), HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/purchases/{idPurchase}")
    public ResponseEntity<List<PurchaseDto>> findPurchaseByUserId(
            @PathVariable @Positive long id,
            @PathVariable @Positive long idPurchase) throws RecourseNotExistException {
        PurchaseDto purchaseDto = purchaseService.findEntityById(id);
        return new ResponseEntity(hateoasWrapper.hateoasWrapperPurchaseDto(purchaseDto), HttpStatus.OK);
    }
}
