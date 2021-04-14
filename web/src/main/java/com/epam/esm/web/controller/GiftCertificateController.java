package com.epam.esm.web.controller;

import com.epam.esm.model.entity.GiftCertificateDto;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exeption.IllegalRequestParameterException;
import com.epam.esm.service.exeption.IllegalRequestSortParameterException;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import java.util.List;
import java.util.Map;

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
    private final CertificateService certificateService;

    @Autowired
    public GiftCertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * Find by id gift certificate.
     *
     * @param id the id
     * @return GiftCertificate
     * @throws RecourseNotExistException if certificate isn't found
     */
    @GetMapping("/{id}")
    public ResponseEntity<GiftCertificateDto> findById(@PathVariable @Positive long id) throws RecourseNotExistException {
        GiftCertificateDto giftCertificate = certificateService.findEntityById(id);
        return new ResponseEntity<>(giftCertificate, HttpStatus.OK);
    }

    /**
     * Delete GiftCertificate
     *
     * @param id the id
     * @throws RecourseNotExistException if  such GiftCertificate id isn't found
     */
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
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GiftCertificateDto> addCertificate(
            @Valid @RequestBody GiftCertificateDto giftCertificate)
            throws RecourseExistException {
        GiftCertificateDto createCertificate = certificateService.add(giftCertificate);
        return new ResponseEntity<>(createCertificate, HttpStatus.CREATED);
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
            @RequestParam Map<String, String> allRequestParam)
            throws IllegalRequestSortParameterException, IllegalRequestParameterException {
        return new ResponseEntity<>(certificateService.findGiftCertificateListByParams(allRequestParam), HttpStatus.FOUND);
    }

    /**
     * Update gift certificate parameters.
     *
     * @param id              the id
     * @param giftCertificate the gift certificate
     * @return the gift certificate dto
     * @throws RecourseNotExistException if gift certificate with such id isn't found
     */
    @PutMapping("/{id}")
    public ResponseEntity<GiftCertificateDto> updateCertificate(
            @PathVariable @Positive long id,
            @RequestBody GiftCertificateDto giftCertificate) throws RecourseNotExistException {
        giftCertificate.setId(id);
        return new ResponseEntity<>(certificateService.update(giftCertificate), HttpStatus.OK);
    }
}
