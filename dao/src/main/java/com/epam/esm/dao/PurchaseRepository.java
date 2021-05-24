package com.epam.esm.dao;

import com.epam.esm.dao.entity.Purchase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Purchase repository.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@Repository
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Long> {
    /**
     * Find purchases by id of user.
     *
     * @param userId   the id of user
     * @param pageable an element of pagination that consists of the number of pages and
     *                 the amount of record on each page.
     * @return the list of purchases
     */
    List<Purchase> findPurchasesByUser_UserId(long userId, Pageable pageable);

    /**
     * Find count purchases by user id.
     *
     * @param userId the id of user
     * @return count
     */
    Long countPurchaseByUser_UserId(long userId);

    /**
     * Find count purchases by user id.
     *
     * @param certificateId the id of user
     * @return count
     */
    @Query(value = "select count(*) from purchase t\n" +
            "where t.purchase_id in(\n" +
            "select p.purchase_id from purchase_certificate p\n" +
            "where p.certificate_id =:certificateId)", nativeQuery = true)
    Long countPurchaseByGiftCertificateId(long certificateId);
}
