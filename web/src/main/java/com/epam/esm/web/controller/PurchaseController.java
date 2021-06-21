package com.epam.esm.web.controller;

import com.epam.esm.model.dto.PurchaseDto;
import com.epam.esm.service.AuthenticatedUserService;
import com.epam.esm.service.PurchaseService;
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
 * The Purchase controller with mapping "/purchases"
 * It used for Rest Api
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/purchases", produces = APPLICATION_JSON_VALUE)
@Validated
public class PurchaseController {
    private static final String DEFAULT_PAGE_N = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";
    private static final String PAGE_N = "pageN";
    private static final String PAGE_SIZE = "pageSize";
    private PurchaseService purchaseService;
    private final AuthenticatedUserService authenticatedUserService;
    private final HateoasWrapper hateoasWrapper;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, AuthenticatedUserService authenticatedUserService, HateoasWrapper hateoasWrapper) {
        this.purchaseService = purchaseService;
        this.authenticatedUserService = authenticatedUserService;
        this.hateoasWrapper = hateoasWrapper;
    }

    /**
     * Find all purchases
     *
     * @return list
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<PurchaseDto>> findAll(
            @Valid @RequestParam(required = false, value = PAGE_N, defaultValue = DEFAULT_PAGE_N)
            int pageN,
            @Valid @RequestParam(required = false, value = PAGE_SIZE, defaultValue = DEFAULT_PAGE_SIZE)
            int pageSize) {
        List<PurchaseDto> list = purchaseService.findAll(pageN, pageSize);
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperListPurchaseDto(list), HttpStatus.OK);
    }

    /**
     * Find by id purchase
     *
     * @param id the id
     * @return Purchase
     * @throws RecourseNotExistException if certificate isn't found
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')||" +
            "(hasAuthority('ROLE_USER')&&@authenticatedUserService.isAuthUserByPurchaseId(#id))")
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDto> findById(@PathVariable @Positive long id) throws RecourseNotExistException {
        PurchaseDto purchaseDto = purchaseService.findEntityById(id);
        return new ResponseEntity(hateoasWrapper.hateoasWrapperPurchaseDto(purchaseDto), HttpStatus.OK);
    }
}
