package com.epam.esm.dao;

import com.epam.esm.dao.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
