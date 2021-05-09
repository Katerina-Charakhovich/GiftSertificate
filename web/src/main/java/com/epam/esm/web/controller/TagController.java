package com.epam.esm.web.controller;

import com.epam.esm.model.dto.TagDto;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import com.epam.esm.web.utils.HateoasWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

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
    private static final String DEFAULT_OFFSET = "0";
    private static final String DEFAULT_LIMIT = "10";
    private static final int MAX_LIMIT = 30;
    private static final int MIN_LIMIT = 1;
    private static final String INVALID_OFFSET_MESSAGE = "invalid value parameter offset";
    private static final String INVALID_LIMIT_MESSAGE = "invalid value parameter limit";
    private static final String OFFSET = "offset";
    private static final String LIMIT = "limit";
    private final TagService tagService;
    private final HateoasWrapper hateoasWrapper;

    @Autowired
    public TagController(TagService tagService, HateoasWrapper hateoasWrapper) {
        this.tagService = tagService;
        this.hateoasWrapper = hateoasWrapper;
    }

    /**
     * Find tag by id
     *
     * @param id the id
     * @return tag
     * @throws RecourseNotExistException if tag isn't found
     */
    @GetMapping("/{id}")
    public ResponseEntity<TagDto> findById(@PathVariable @Positive long id) throws RecourseNotExistException {
        TagDto tagDto = tagService.findEntityById(id);
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperTagDto(tagDto), HttpStatus.OK);
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
    public ResponseEntity<TagDto> addTag(@Valid @RequestBody TagDto tagDto
    ) throws RecourseExistException, RecourseNotExistException {
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperTagDto(tagService.add(tagDto)), HttpStatus.CREATED);
    }

    /**
     * Find all tags
     *
     * @return list
     */
    @GetMapping
    public ResponseEntity<List<TagDto>> findAll(
            @Valid @RequestParam(required = false, value = OFFSET, defaultValue = DEFAULT_OFFSET)
            @Min(value = 0, message = INVALID_OFFSET_MESSAGE) int offset,
            @Valid @RequestParam(required = false, value = LIMIT, defaultValue = DEFAULT_LIMIT)
            @Min(value = MIN_LIMIT, message = INVALID_LIMIT_MESSAGE)
            @Max(value = MAX_LIMIT, message = INVALID_LIMIT_MESSAGE) int limit) {
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperListTagDto(tagService.findAll(offset, limit)), HttpStatus.OK);
    }

    /**
     * Find most popular tag
     *
     * @return tag
     */
    @GetMapping(value = "/popular")
    public ResponseEntity<TagDto> findPopularTag() {
        Optional<TagDto> tagDtoOpt = tagService.findPopularTag();
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperTagDto(tagDtoOpt.get()), HttpStatus.OK);
    }
}
