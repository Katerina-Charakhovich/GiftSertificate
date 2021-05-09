package com.epam.esm.service;

import com.epam.esm.model.dto.TagDto;

import java.util.Optional;
/**
 * The interface Tag service.
 */
public interface TagService extends CommonService<TagDto> {
   /**
    * Find most popular tag.
    *
    * @return optional
    */
   Optional<TagDto> findPopularTag();
}
