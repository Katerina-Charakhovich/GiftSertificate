package com.epam.esm.dao.mapper;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 * The Certificate Extractor
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@Component
public class CertificateExtractor implements ResultSetExtractor<List <GiftCertificate>> {

    @Override
    public List<GiftCertificate> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<GiftCertificate, List<Tag>> data = new LinkedHashMap<>();
        while (rs.next()) {
            GiftCertificate giftCertificate = new CertificateMapper().mapRow(rs, rs.getRow());
            data.putIfAbsent(giftCertificate, new ArrayList<>());
            Optional<Tag> tag = new TagMapper().mapRow(rs, rs.getRow());
            tag.ifPresent(value -> data.get(giftCertificate).add(value));
        }
        List <GiftCertificate> certificateList=new ArrayList<>();
        for (Map.Entry<GiftCertificate, List<Tag>> entry :data.entrySet()
        ) {
            GiftCertificate certificate=entry.getKey();
            certificate.setListTag(entry.getValue());
            certificateList.add(certificate);
        }
        return certificateList;
    }


}