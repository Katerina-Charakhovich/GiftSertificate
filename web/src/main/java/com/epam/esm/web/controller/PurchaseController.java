package com.epam.esm.web.controller;

import com.epam.esm.model.dto.PurchaseDto;
import com.epam.esm.service.PurchaseService;
import com.epam.esm.service.exeption.RecourseNotExistException;
import com.epam.esm.web.utils.HateoasWrapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
@AllArgsConstructor
@RequestMapping(value = "/purchases", produces = APPLICATION_JSON_VALUE)
@Validated
public class PurchaseController {
    private static final String DEFAULT_OFFSET = "0";
    private static final String DEFAULT_LIMIT = "10";
    private static final String INVALID_OFFSET_MESSAGE = "invalid value parameter offset";
    private static final String INVALID_LIMIT_MESSAGE = "invalid value parameter limit";
    private static final String OFFSET = "offset";
    private static final String LIMIT = "limit";
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    HateoasWrapper hateoasWrapper;

    /**
     * Find all users
     *
     * @return list
     */
    @GetMapping
    public ResponseEntity<List<PurchaseDto>> findAll(
            @Valid @RequestParam(required = false, value = OFFSET, defaultValue = DEFAULT_OFFSET)
            @Min(value = 0, message = INVALID_OFFSET_MESSAGE) int offset,
            @Valid @RequestParam(required = false, value = LIMIT, defaultValue = DEFAULT_LIMIT)
            @Min(value = 1, message = INVALID_LIMIT_MESSAGE) int limit) {
        List<PurchaseDto> list = purchaseService.findAll(offset, limit);
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperListPurchaseDto(list), HttpStatus.OK);
    }

    /**
     * Find by id gift certificate.
     *
     * @param id the id
     * @return Purchase
     * @throws RecourseNotExistException if certificate isn't found
     */
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDto> findById(@PathVariable @Positive long id) throws RecourseNotExistException {
        PurchaseDto purchaseDto = purchaseService.findEntityById(id);
        return new ResponseEntity(hateoasWrapper.hateoasWrapperPurchaseDto(purchaseDto), HttpStatus.OK);
    }
}