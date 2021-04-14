package com.epam.esm.dao.mapper;

import com.epam.esm.dao.entity.Tag;
import com.epam.esm.model.parameters.TableColumnName;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The  Tag RowMapper.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@Component
public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong(TableColumnName.TAG_DAO_ID);
        String name = rs.getString(TableColumnName.TAG_DAO_NAME);
        Tag tag = new Tag();
        tag.setTagId(rs.getLong("id_tag"));
        tag.setTagName(rs.getString("name_tag"));
        return tag;
    }
}



