package com.epam.esm.dao.mapper;


import com.epam.esm.model.entity.Tag;
import com.epam.esm.model.parameters.TableColumnName;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * The  Tag RowMapper.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@Component
public class TagMapper implements RowMapper<Optional<Tag>> {
    @Override
    public Optional<Tag> mapRow(ResultSet rs, int rowNum) throws SQLException {
        Optional<Tag> optionalTag=Optional.empty();
        long id=  rs.getLong(TableColumnName.TAG_DAO_ID);
        String name=rs.getString(TableColumnName.TAG_DAO_NAME);
        if (id>0){
            Tag tag = new Tag();
            tag.setTagId(rs.getLong("id_tag"));
            tag.setTagName(rs.getString("name_tag"));
            optionalTag=Optional.of(tag);
        }
        return optionalTag;
    }
}

