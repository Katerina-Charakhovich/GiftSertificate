package com.epam.esm.dao;

import com.epam.esm.dao.entity.Tag;
import com.epam.esm.model.dto.TagDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
/**
 * The interface Tag repository.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {
    /**
     * Find Tag by name
     *
     * @param tagName Tag name
     * @return optional tag
     */
    Optional<Tag> findByTagName(String tagName);

    /**
     * Find most popular tag
     *
     * @return optional tag
     */
    @Query(value = "select tag.id_tag,tag.name_tag,g.cnt from tag, (select t2.id_tag as tag_id,t2.name_tag as name_tag, count(*) as cnt,sum(t.price)  " +
            "from purchase p " +
            "         join  purchase_certificate p1 on p.purchase_id=p1.purchase_id " +
            "         join certificate t on  p1.certificate_id=t.certificate_id " +
            "         join certificate_tag t1 on t.certificate_id=t1.certificate_id " +
            "         join tag t2 on t1.id_tag = t2.id_tag " +
            "group by t2.id_tag " +
            "order by 3 desc,4 desc LIMIT 1 ) g  " +
            "where tag.id_tag=g.tag_id;", nativeQuery = true)
    Optional<Tag> findPopularTag();
}
