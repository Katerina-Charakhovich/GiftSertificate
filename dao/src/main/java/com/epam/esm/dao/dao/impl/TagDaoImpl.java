package com.epam.esm.dao.dao.impl;

import com.epam.esm.dao.dao.BaseDao;
import com.epam.esm.dao.dao.TagDao;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.mapper.TagMapper;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl extends BaseDao<Tag> implements TagDao {
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(TagDaoImpl.class);
    private final JdbcTemplate jdbcTemplate;
    private final TagMapper tagMapper;
    private static final String FIND_TAG_BY_ID = "select id_tag, name_tag  from  tag where id_tag=?";

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate, TagMapper tagMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagMapper = tagMapper;
    }

    @Override
    public Optional<Tag> findEntityById(long id) {
        Optional<Tag> optionalTag = Optional.empty();
        ResultSetExtractor<Tag> rs = null;
        try {
            List<Tag> tag = jdbcTemplate.query(FIND_TAG_BY_ID, tagMapper, new Object[]{id});
            optionalTag = Optional.of(tag.get(0));
        } catch (DataAccessException dataAccessException) {
            System.out.println();
        }
        return optionalTag;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public int create(Tag entity) {
        return 0;
    }

    @Override
    public int update(Tag entity) {
        return 0;
    }

    @Override
    public Optional<Tag> findByName(String tagName) {
        return Optional.empty();
    }

    @Override
    public List<Tag> findByCertificateId(long certificateId) {
        return null;
    }
}
