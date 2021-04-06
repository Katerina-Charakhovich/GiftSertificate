package com.epam.esm.web.controller;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

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
    public ResponseEntity<GiftCertificate> findById(@PathVariable @Positive long id) throws RecourseNotExistException {
        ;
        GiftCertificate giftCertificate = certificateService.findEntityById(id);
        return new ResponseEntity<GiftCertificate>(giftCertificate, HttpStatus.OK);
    }

    /**
     * Delete GiftCertificate
     *
     * @param id the id
     * @throws RecourseNotExistException if  such GiftCertificate id isn't found
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificate(@PathVariable @Positive long id) throws RecourseNotExistException {
        certificateService.delete(id);
    }

    /**
     * Add new GiftCertificate
     *
     * @param giftCertificate
     * @throws RecourseExistException  such GiftCertificate is exist
     */
    @PostMapping
    public ResponseEntity< GiftCertificate> addCertificate(@RequestBody GiftCertificate giftCertificate ) throws RecourseExistException {
            return new ResponseEntity< >(HttpStatus.CREATED);
    }
}
