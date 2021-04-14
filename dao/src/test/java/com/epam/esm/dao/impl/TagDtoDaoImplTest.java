package com.epam.esm.dao.impl;

import com.epam.esm.dao.converter.impl.TagConverter;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.model.entity.TagDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TagDtoDaoImplTest {
    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private TagDaoImpl tagDaoImpl;

    @BeforeEach
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql").addScript("classpath:data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        tagDaoImpl = new TagDaoImpl(jdbcTemplate, new TagMapper(), new TagConverter());
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    void findEntityById() {
        long tagId = 1;
        assertNotNull(tagDaoImpl.findEntityById(tagId));
    }

    @Test
    void delete() {
        long tagId = 1;
        tagDaoImpl.delete(tagId);
        assertEquals(Optional.empty(), tagDaoImpl.findEntityById(tagId));
    }

    @Test
    void create() {
        TagDto tagDto = new TagDto();
        tagDto.setTagName("test");
        tagDto.setTagId(tagDaoImpl.create(tagDto).getTagId());
        assertEquals(tagDto, tagDaoImpl.findEntityById(tagDto.getTagId()).get());
    }

    @Test
    void findByName() {
        TagDto tagDto = new TagDto();
        tagDto.setTagName("test");
        tagDto.setTagId(tagDaoImpl.create(tagDto).getTagId());
        assertEquals(tagDto.getTagName(), tagDaoImpl.findEntityById(tagDto.getTagId()).get().getTagName());
    }
}
