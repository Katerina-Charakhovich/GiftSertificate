package com.epam.esm.dao;

import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.model.dto.StateCertificate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface GiftCertificate repository.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@Repository
public interface GiftCertificateRepository extends PagingAndSortingRepository<GiftCertificate, Long>,
        QuerydslPredicateExecutor<GiftCertificate> {
    /**
     * Find gift certificate by name in Db.
     *
     * @param name is the name of gift certificate
     * @param state is the state of gift certificate
     *             state
     * @return the optional of gift certificate
     */
    Optional<GiftCertificate> findByName_AndState(String name, StateCertificate state);
    /**
     * Find gift certificate by name in Db.
     *
     * @param pageable an element of pagination that consists of the number of pages and
     *                 the amount of record on each page.
     * @param state is the state of gift certificate
     *             state
     * @return list
     */
    List<GiftCertificate> findByState(StateCertificate state, Pageable pageable);
}

