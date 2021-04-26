package com.epam.esm.dao.impl;

import com.epam.esm.dao.converter.TagConverter;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.model.dto.TagDto;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TagDaoImpl implements TagDao {

    @PersistenceContext
    private EntityManager entityManager;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(TagDaoImpl.class);
    public static final String SELECT_ALL_TAG = "FROM Tag";
    private static final String FIND_TAG_BY_NAME = "FROM Tag WHERE tagName=:tagName";
    private static final String SELECT_TOP_TAG = "select tag.id_tag,tag.name_tag,g.cnt from tag, " +
            "(select t2.id_tag as tag_id,t2.name_tag as name_tag, count(*) as cnt,sum(t.price) from certificate t " +
            " join certificate_tag t1 on t.certificate_id=t1.certificate_id " +
            " join tag t2 on t1.id_tag = t2.id_tag group by t2.id_tag " +
            "order by 3 desc,4 desc LIMIT 1 ) g " +
            "where tag.id_tag=g.tag_id";

    @Override
    public Optional<TagDto> findEntityById(long id) {
        Tag tag = entityManager.find(Tag.class, id);
        return tag != null ? Optional.ofNullable(TagConverter.convertTo(tag)) : Optional.empty();
    }

    @Override
    public void delete(TagDto tagDto) {
        Tag tag = entityManager.find(Tag.class, tagDto.getTagId());
        entityManager.remove(tag);
        entityManager.flush();
    }

    @Override
    public TagDto create(TagDto entity) {
        Tag tag = TagConverter.convertFrom(entity);
        entityManager.persist(tag);
        entity.setTagId(tag.getTagId());
        return entity;
    }

    @Override
    public TagDto update(TagDto entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TagDto> findAll(int offset, int limit) {
        List<Tag> listTag = entityManager.createQuery(SELECT_ALL_TAG, Tag.class)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
        ;
        return TagConverter.convertTo(listTag);
    }

    @Override
    public Optional<TagDto> findByName(String tagName) {
        Query query = entityManager.createQuery(FIND_TAG_BY_NAME).setParameter("tagName", tagName);
        List<Tag> listTag = query.getResultList();
        return listTag.size() != 0 ? Optional.of(TagConverter.convertTo(listTag).get(0)) : Optional.empty();
    }

    @Override
    public Optional<TagDto> findPopularTag() {
        List<Tag> listTag = entityManager.createNativeQuery(SELECT_TOP_TAG, Tag.class).getResultList();
        return listTag.size() != 0 ? Optional.of(TagConverter.convertTo(listTag).get(0)) : Optional.empty();
    }
}
