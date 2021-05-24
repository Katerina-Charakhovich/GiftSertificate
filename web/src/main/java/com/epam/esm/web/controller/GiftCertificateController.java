package com.epam.esm.web.controller;

import com.epam.esm.model.dto.GiftCertificateDto;
import com.epam.esm.model.dto.GiftCertificateUpdateDto;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exeption.IllegalRequestParameterException;
import com.epam.esm.service.exeption.IllegalRequestSortParameterException;
import com.epam.esm.service.exeption.RecourseExistException;
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
import java.util.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * The GiftCertificate controller with mapping "/certificates"
 * It used for Rest Api
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/certificates", produces = APPLICATION_JSON_VALUE)
@Validated
public class GiftCertificateController {
    private static final String DEFAULT_PAGE_N = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";
    private static final String PAGE_N = "pageN";
    private static final String PAGE_SIZE = "pageSize";
    private static final String COMMA = ",";
    private final CertificateService certificateService;
    private final HateoasWrapper hateoasWrapper;

    @Autowired
    public GiftCertificateController(CertificateService certificateService, HateoasWrapper hateoasWrapper) {
        this.certificateService = certificateService;
        this.hateoasWrapper = hateoasWrapper;
    }

    /**
     * Find by id gift certificate.
     *
     * @param id the id
     * @return GiftCertificate
     * @throws RecourseNotExistException if certificate isn't found
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<GiftCertificateDto> findById(@PathVariable @Positive long id) throws RecourseNotExistException {
        GiftCertificateDto giftCertificate = certificateService.findEntityById(id);
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperGiftCertificateDto(giftCertificate), HttpStatus.OK);
    }

    /**
     * Delete GiftCertificate
     *
     * @param id the id
     * @throws RecourseNotExistException if  such GiftCertificate id isn't found
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificate(
            @PathVariable @Positive long id)
            throws RecourseNotExistException {
        certificateService.delete(id);
    }

    /**
     * Add new gift certificate
     *
     * @param giftCertificate param the gift certificate
     * @throws RecourseExistException such GiftCertificate is exist
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GiftCertificateDto> addCertificate(
            @Valid @RequestBody GiftCertificateDto giftCertificate)
            throws RecourseExistException, RecourseNotExistException {
        GiftCertificateDto createCertificate = certificateService.add(giftCertificate);
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperGiftCertificateDto(createCertificate), HttpStatus.CREATED);
    }

    /**
     * Find gift certificates by params
     *
     * @param allRequestParam the parameters
     * @return the list
     * @throws IllegalRequestParameterException     if parameters aren't from list
     * @throws IllegalRequestSortParameterException if sort parameters aren't from list
     */
    @GetMapping
    public ResponseEntity<List<GiftCertificateDto>> findListCertificate(
            @Valid @RequestParam(required = false, value = PAGE_N, defaultValue = DEFAULT_PAGE_N)
                    int pageN,
            @Valid @RequestParam(required = false, value = PAGE_SIZE, defaultValue = DEFAULT_PAGE_SIZE)
                    int pageSize,
            @RequestParam Map<String, String> allRequestParam)
            throws IllegalRequestSortParameterException,
            IllegalRequestParameterException {
        allRequestParam.remove(PAGE_SIZE);
        allRequestParam.remove(PAGE_N);
        Map<String, List<String>> params = new HashMap<>();
        for (Map.Entry<String, String> entry : allRequestParam.entrySet()
        ) {
            params.put(entry.getKey(), Arrays.asList(entry.getValue().split(COMMA)));
        }
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperListGiftCertificateDto(
                certificateService.findGiftCertificateListByParams(params, pageN, pageSize)), HttpStatus.FOUND);
    }

    /**
     * Update gift certificate parameters.
     *
     * @param id              the id
     * @param giftCertificate the gift certificate
     * @return the gift certificate dto
     * @throws RecourseNotExistException if gift certificate with such id isn't found
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<GiftCertificateDto> updateCertificate(
            @PathVariable @Positive long id,
            @RequestBody GiftCertificateUpdateDto giftCertificate) throws RecourseNotExistException {
        giftCertificate.setId(id);
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperGiftCertificateDto(certificateService.update(giftCertificate)), HttpStatus.OK);
    }
}
