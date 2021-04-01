package com.epam.esm.service.impl;

import com.epam.esm.dao.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

class TagServiceImplTest {
    @Autowired
    TagServiceImpl tagService;

    @Test
    void findEntityById() {
        Optional<Tag> tag=tagService.findEntityById(1);
    }
}