package com.epam.esm.dao.dao.impl;

import com.epam.esm.dao.dao.TagDao;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.model.entity.Tag;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl implements TagDao {
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(TagDaoImpl.class);
    private final JdbcTemplate jdbcTemplate;
    private final TagMapper tagMapper;
    private static final String FIND_TAG_BY_ID = "select tag.id_tag, tag.name_tag  from  tag where tag.id_tag=?";
    private static final String FIND_TAGS_BY_CERTIFICATE_ID = "select t.id_tag, t.name_tag  from  tag t" +
            " join certificate_tag t1 on t.id_tag=t1.id_tag" +
            " where t1.certificate_id=?";
    private static final String FIND_TAG_BY_NAME = "select id_tag, name_tag  from tag where name_tag like ?";
    private static final String DELETE_TAG_BY_ID = "delete from tag where id_tag=?";
    public static final String ADD_TAG = "insert into tag (name_tag) values (?)";

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate, TagMapper tagMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagMapper = tagMapper;
    }

    @Override
    public Optional<Tag> findEntityById(long id) {
        List<Optional<Tag>> tag = jdbcTemplate.query(FIND_TAG_BY_ID, tagMapper, id);
        return tag.isEmpty() ? Optional.empty() : tag.get(0);
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE_TAG_BY_ID, id) > 0;

    }

    @Override
    public Tag create(Tag entity) {
        KeyHolder key = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_TAG,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getTagName());
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, key);
        Number id = key.getKey();
        if (id != null) {
            entity.setTagId(id.longValue());
        }
        return entity;
    }

    @Override
    public int update(Tag entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Tag> findByName(String tagName) {
        List<Optional<Tag>> tag = jdbcTemplate.query(FIND_TAG_BY_NAME, tagMapper, tagName);
        return tag.isEmpty() ? Optional.empty() : tag.get(0);
    }

    @Override
    public List<Tag> findListByCertificateId(long certificateId) {
        List<Optional<Tag>> listOptionalTag = jdbcTemplate.query(FIND_TAGS_BY_CERTIFICATE_ID, tagMapper, certificateId);
        List<Tag> listTag = new ArrayList<>();
        for (Optional<Tag> optionalTag : listOptionalTag
        ) {
            listTag.add(optionalTag.get());
        }
        return listTag;
    }


}
