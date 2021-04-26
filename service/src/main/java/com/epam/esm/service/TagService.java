package com.epam.esm.service;

import com.epam.esm.model.dto.TagDto;

import java.util.Optional;

public interface TagService extends CommonService<TagDto> {
   Optional<TagDto> findPopularTag();
}
