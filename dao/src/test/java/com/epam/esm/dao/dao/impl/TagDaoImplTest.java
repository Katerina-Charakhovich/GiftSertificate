package com.epam.esm.dao.dao.impl;


import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.model.entity.Tag;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.Optional;

/*@SpringJUnitConfig(DaoConfig.class)*/
class TagDaoImplTest {
 /*   @Autowired
    private TagDaoImpl tagDaoImpl;*/

    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private TagDaoImpl tagDaoImpl;

    @BeforeEach
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql").addScript("classpath:data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        tagDaoImpl = new TagDaoImpl(jdbcTemplate, new TagMapper());
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }
    @Test
    void findEntityById() {
        var tag=tagDaoImpl.findEntityById(1);
        Assert.assertNotNull(tagDaoImpl.findEntityById(1));
    }

    @Test
    void delete() {
        long deleteId=1;
        tagDaoImpl.delete(deleteId);
        Assert.assertEquals(Optional.empty(),tagDaoImpl.findEntityById(1));
    }

    @Test
    void create() {
        Tag tag=new Tag();
        tag.setTagName("test");
        tag.setTagId(tagDaoImpl.create(tag).getTagId());
        Assert.assertEquals(tag,tagDaoImpl.findEntityById(tag.getTagId()).get());
    }

    @Test
    void findByName() {
        Tag tag=new Tag();
        tag.setTagName("test");
        tag.setTagId(tagDaoImpl.create(tag).getTagId());
        Assert.assertEquals(tag.getTagName(),tagDaoImpl.findEntityById(tag.getTagId()).get().getTagName());
    }
}