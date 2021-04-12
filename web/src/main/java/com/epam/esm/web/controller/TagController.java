package com.epam.esm.web.controller;

import com.epam.esm.model.entity.TagDto;
import com.epam.esm.model.entity.ViewProfileJackson;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * The  Tag controller with mapping "/tags"
 * It used for Rest Api
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/tags", produces = APPLICATION_JSON_VALUE)
@Validated
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Find tag by id
     *
     * @param id the id
     * @return tag
     * @throws RecourseNotExistException if tag isn't found
     */
    @GetMapping("/{id}")
    @JsonView(ViewProfileJackson.GetRecourse.class)
    public ResponseEntity<TagDto> findById(@PathVariable @Positive long id) throws RecourseNotExistException {
        TagDto tagDto = tagService.findEntityById(id);
        return new ResponseEntity<>(tagDto, HttpStatus.OK);
    }

    /**
     * Delete tag
     *
     * @param id the id
     * @throws RecourseNotExistException if  such GiftCertificate id isn't found
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTag(@PathVariable @Positive long id) throws RecourseNotExistException {
        tagService.delete(id);
    }

    /**
     * Add tag
     *
     * @return the tag
     */
    @PostMapping
    @JsonView(ViewProfileJackson.UpdateAndCreateRecourse.class)
    public ResponseEntity<TagDto> addTag(@Valid @RequestBody TagDto tagDto
    ) throws RecourseExistException {
        return new ResponseEntity<>(tagService.add(tagDto), HttpStatus.CREATED);
    }
}
