package com.epam.esm.dao;

import com.epam.esm.dao.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * The interface User repository.
 *
 * @author Katerina Charakhovich
 * @version 1.0.0
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    /**
     * Find user by login.
     *
     * @param login is the the login of user
     * @return the optional of user
     */
    Optional<User> findByLogin(String login);
}
