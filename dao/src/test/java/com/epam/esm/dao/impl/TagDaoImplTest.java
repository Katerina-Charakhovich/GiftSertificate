package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagRepository;
import com.epam.esm.dao.config.TestConfig;
import com.epam.esm.dao.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testDao")
@ContextConfiguration(classes = TestConfig.class)
@Sql({"classpath:drop.sql", "classpath:schema.sql", "classpath:data.sql"})
class TagDaoImplTest {
    @Autowired
    private TagRepository tagRepository;

    @Test
    void findEntityById() {
        long tagId = 1;
        assertNotNull(tagRepository.findById(tagId));
    }

    @Test
    void findAll() {
      Page<Tag> tags=  tagRepository.findAll(PageRequest.of(0,1));
        assertNotNull(tags);
    }

    @Test
    @Transactional
    void delete() {
        Tag tag = new Tag();
        tag.setTagName("testDelete");
        Long tagId = tagRepository.save(tag).getTagId();
        tag.setTagId(tagId);
        tagRepository.delete(tag);
        assertEquals(Optional.empty(), tagRepository.findById(tagId));
    }

    @Test
    @Transactional
    void create() {
        Tag tag = new Tag();
        tag.setTagName("test");
        tag.setTagId(tagRepository.save(tag).getTagId());
        assertEquals(tag, tagRepository.findById(tag.getTagId()).get());
    }
    @Test
    @Transactional
    void findByName() {
        Tag tag = new Tag();
        tag.setTagName("test");
        tag.setTagId(tagRepository.save(tag).getTagId());
        Optional<Tag> tagDto1 = tagRepository.findByTagName("relax");
        assertEquals(tag.getTagName(), tagRepository.findById(tag.getTagId()).get().getTagName());
    }
}
