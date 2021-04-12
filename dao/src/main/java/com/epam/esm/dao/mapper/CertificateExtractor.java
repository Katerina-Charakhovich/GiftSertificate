package com.epam.esm.dao.mapper;

import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CertificateExtractor implements ResultSetExtractor<List<GiftCertificate>> {
    private final TagMapper tagMapper;
    private final CertificateMapper certificateMapper;

    @Autowired
    public CertificateExtractor(TagMapper tagMapper, CertificateMapper certificateMapper) {
        this.tagMapper = tagMapper;
        this.certificateMapper = certificateMapper;
    }

    @Override
    public List<GiftCertificate> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<GiftCertificate, List<Tag>> data = new LinkedHashMap<>();
        while (rs.next()) {
            GiftCertificate giftCertificate = certificateMapper.mapRow(rs, rs.getRow());
            data.putIfAbsent(giftCertificate, new ArrayList<>());
            Tag tag = tagMapper.mapRow(rs, rs.getRow());
            if (tag.getTagId()!=0){
                data.get(giftCertificate).add(tag);
            }
        }
        List<GiftCertificate> certificateList = new ArrayList<>();
        for (Map.Entry<GiftCertificate, List<Tag>> entry : data.entrySet()
        ) {
            GiftCertificate certificate = entry.getKey();
            certificate.setListTag(entry.getValue());
            certificateList.add(certificate);
        }
        return certificateList;
    }
}
