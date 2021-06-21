package com.epam.esm.web.controller;

import com.epam.esm.model.dto.TagDto;
import com.epam.esm.service.TagService;
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
    private static final String DEFAULT_PAGE_N = "0";
    private static final String DEFAULT_PAGE_SIZE = "5";
    private static final String PAGE_N = "pageN";
    private static final String PAGE_SIZE = "pageSize";
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
    @PreAuthorize("isAuthenticated()")
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
            @Valid @RequestParam(required = false, value = PAGE_N, defaultValue = DEFAULT_PAGE_N)
                    int pageN,
            @Valid @RequestParam(required = false, value = PAGE_SIZE, defaultValue = DEFAULT_PAGE_SIZE)
                    int pageSize) {
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperListTagDto(tagService.findAll(pageN, pageSize)), HttpStatus.OK);
    }

    /**
     * Find most popular tag
     *
     * @return tag
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/popular")
    public ResponseEntity<TagDto> findPopularTag() {
        Optional<TagDto> tagDtoOpt = tagService.findPopularTag();
        return new ResponseEntity<>(hateoasWrapper.hateoasWrapperTagDto(tagDtoOpt.get()), HttpStatus.OK);
    }
}
