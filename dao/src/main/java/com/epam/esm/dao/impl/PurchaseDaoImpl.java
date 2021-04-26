package com.epam.esm.dao.impl;

import com.epam.esm.dao.PurchaseDao;
import com.epam.esm.dao.converter.PurchaseConverter;
import com.epam.esm.dao.entity.Purchase;
import com.epam.esm.model.dto.PurchaseShortDto;
import com.epam.esm.model.dto.PurchaseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PurchaseDaoImpl implements PurchaseDao {
    @PersistenceContext
    private EntityManager entityManager;
    public static final String SELECT_ALL_PURCHASE = "FROM Purchase";
    public static final String FIND_BY_CERTIFICATE_ID = "FROM Purchase t " +
            "JOIN FETCH t.user t1 "
            + "WHERE t1.userId = :userId ";

    @Override
    public Optional<PurchaseDto> findEntityById(long id) {
        Purchase purchase = entityManager.find(Purchase.class, id);
        return purchase != null ? Optional.ofNullable(PurchaseConverter.convertTo(purchase)) : Optional.empty();
    }

    @Override
    public void delete(PurchaseDto entity) {
        Purchase purchase = entityManager.find(Purchase.class, entity.getId());
        entityManager.remove(purchase);
    }

    @Override
    public PurchaseDto create(PurchaseDto entity) {
        Purchase purchase = PurchaseConverter.convertFrom(entity);
        purchase.setCreateDate(LocalDateTime.now());
        purchase.setLastUpdateTime(LocalDateTime.now());
        entityManager.merge(purchase);
        return PurchaseConverter.convertTo(purchase);
    }

    @Override
    public PurchaseDto update(PurchaseDto entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PurchaseDto> findAll(int offset, int limit) {
        List<Purchase> listPurchase = entityManager.createQuery(SELECT_ALL_PURCHASE, Purchase.class)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
        return PurchaseConverter.convertTo(listPurchase);
    }

    @Override
    public PurchaseShortDto addPurchaseDto(PurchaseShortDto purchaseShortDto) {
        Purchase purchase = PurchaseConverter.convertShortFrom(purchaseShortDto);
        purchase.setCreateDate(LocalDateTime.now());
        purchase.setLastUpdateTime(LocalDateTime.now());
        entityManager.persist(purchase);
        purchase.getPurchaseId();
        entityManager.flush();
        return PurchaseConverter.convertShortTo(purchase);
    }

    @Override
    public List<PurchaseDto> findByUserId(long id) {
        List<Purchase> listPurchase = entityManager.createQuery(FIND_BY_CERTIFICATE_ID, Purchase.class)
                .setParameter("userId", id).getResultList();
        return PurchaseConverter.convertTo(listPurchase);
    }
}
