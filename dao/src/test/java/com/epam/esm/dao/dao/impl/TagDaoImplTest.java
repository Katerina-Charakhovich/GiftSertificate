package com.epam.esm.dao.dao.impl;

import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.mapper.TagMapper;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.Optional;

class TagDaoImplTest {
  private EmbeddedDatabase embeddedDatabase;

    private JdbcTemplate jdbcTemplate;

    private TagDaoImpl tagDaoImpl;

    @BeforeEach
    public void setUp() {
        // Создадим базу данных для тестирования
        embeddedDatabase = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql").addScript("classpath:data.sql")// Добавляем скрипты schema.sql и data.sql
                .build();

        // Создадим JdbcTemplate
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);

        // Создадим PersonRepository
        tagDaoImpl = new TagDaoImpl(jdbcTemplate,new TagMapper());
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

/* @Autowired
 private TagDaoImpl tagDaoImpl;
*/
    @Test
    void findEntityById() {
        Optional<Optional<Tag>> tag= Optional.of(tagDaoImpl.findEntityById(1));
        Assert.assertNotNull(tagDaoImpl.findEntityById(1));
    }
}