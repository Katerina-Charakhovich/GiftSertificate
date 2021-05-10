package com.epam.esm.dao.impl;

import com.epam.esm.dao.config.TestConfig;
import com.epam.esm.model.dto.TagDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testt")
//@ContextConfiguration(classes = TestConfig.class)
//@Sql({"classpath:drop.sql", "classpath:schema.sql", "classpath:data.sql"})
class TagDaoImplTest {
/*    @Autowired
    private TagDaoImpl tagDaoImpl;

    @Test
    void findEntityById() {
        long tagId = 1;
        assertNotNull(tagDaoImpl.findEntityById(tagId));
    }

    @Test
    void findAll() {
        List<TagDto> tagDtoList = tagDaoImpl.findAll(1, 2);
        assertNotNull(tagDtoList);
    }

    @Test
    @Transactional
    void delete() {
        TagDto tagDto = new TagDto();
        tagDto.setTagName("testDelete");
        Long tagId = tagDaoImpl.create(tagDto).getTagId();
        tagDto.setTagId(tagId);
        tagDaoImpl.delete(tagDto);
        assertEquals(Optional.empty(), tagDaoImpl.findEntityById(tagId));
    }

    @Test
    @Transactional
    void create() {
        TagDto tagDto = new TagDto();
        tagDto.setTagName("test");
        tagDto.setTagId(tagDaoImpl.create(tagDto).getTagId());
        assertEquals(tagDto, tagDaoImpl.findEntityById(tagDto.getTagId()).get());
    }

    @Test
    @Transactional
    void findByName() {
        TagDto tagDto = new TagDto();
        tagDto.setTagName("test");
        tagDto.setTagId(tagDaoImpl.create(tagDto).getTagId());
        Optional<TagDto> tagDto1 = tagDaoImpl.findByName("relax");
        assertEquals(tagDto.getTagName(), tagDaoImpl.findEntityById(tagDto.getTagId()).get().getTagName());
    }*/
}
