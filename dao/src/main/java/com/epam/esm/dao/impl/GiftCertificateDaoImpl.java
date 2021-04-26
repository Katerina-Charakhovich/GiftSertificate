package com.epam.esm.dao.impl;

import com.epam.esm.dao.TableName;
import com.epam.esm.dao.builder.BuilderSql;
import com.epam.esm.dao.converter.GiftCertificateConverter;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.model.dto.GiftCertificateDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final String FIND_BY_NAME = "FROM GiftCertificate WHERE name=:name";
    private static final String FIND_CERTIFICATES_BY_PARAMS = "FROM GiftCertificate " + TableName.CERTIFICATE_TABLE + " LEFT JOIN FETCH " + TableName.CERTIFICATE_TABLE + ".listTag " +
            TableName.TAG_TABLE;
    private static final String SELECT_ALL_CERTIFICATE = "FROM GiftCertificate";

    @Override
    public Optional<GiftCertificateDto> findEntityById(long id) {
        GiftCertificate giftCertificate = entityManager.find(GiftCertificate.class, id);
        return giftCertificate != null ? Optional.ofNullable(GiftCertificateConverter.convertTo(giftCertificate)) : Optional.empty();
    }

    @Override
    public void delete(GiftCertificateDto entity) {
        GiftCertificate giftCertificate = entityManager.find(GiftCertificate.class, entity.getId());
        entityManager.remove(giftCertificate);
    }

    @Override
    public List<GiftCertificateDto> findEntityByParams(Map<String, List<String>> groupParams, int offset, int limit) {
        String query = FIND_CERTIFICATES_BY_PARAMS + BuilderSql.buildFindAndSortCertificateByParameter(groupParams);
        List<GiftCertificate> listGiftCertificate = entityManager.createQuery(query, GiftCertificate.class)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
        return GiftCertificateConverter.convertTo(listGiftCertificate);
    }

    @Override
    public Optional<GiftCertificateDto> findByName(String certificateName) {
        List<GiftCertificate> listGiftCertificate = entityManager.createQuery(FIND_BY_NAME).setParameter("name", certificateName).getResultList();
        return listGiftCertificate.size() == 0 ? Optional.empty() : Optional.ofNullable(GiftCertificateConverter.convertTo(listGiftCertificate.get(0)));
    }

    @Override
    public GiftCertificateDto create(GiftCertificateDto entity) {
        GiftCertificate giftCertificate = GiftCertificateConverter.convertFrom(entity);
        entityManager.persist(giftCertificate);
        giftCertificate.getId();
        return GiftCertificateConverter.convertTo(giftCertificate);
    }

    @Override
    public GiftCertificateDto update(GiftCertificateDto entity) {
        GiftCertificate giftCertificate = GiftCertificateConverter.convertFrom(entity);
        entityManager.merge(giftCertificate);
        entityManager.flush();
        return GiftCertificateConverter.convertTo(giftCertificate);
    }

    @Override
    public List<GiftCertificateDto> findAll(int offset, int limit) {
        List<GiftCertificate> listGiftCertificate = entityManager.createQuery(SELECT_ALL_CERTIFICATE, GiftCertificate.class)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
        return GiftCertificateConverter.convertTo(listGiftCertificate);
    }
}

