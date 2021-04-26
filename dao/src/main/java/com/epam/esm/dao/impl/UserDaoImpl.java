package com.epam.esm.dao.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.converter.UserConverter;
import com.epam.esm.dao.entity.User;
import com.epam.esm.model.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    public static final String SELECT_ALL_USER = "FROM User";

    @Override
    public Optional<UserDto> findEntityById(long id) {
        User user = entityManager.find(User.class, id);
        return user != null ? Optional.ofNullable(UserConverter.convertTo(user)) : Optional.empty();
    }

    @Override
    public void delete(UserDto entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserDto create(UserDto entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserDto update(UserDto entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserDto> findAll(int offset, int limit) {
        List<User> listUser = entityManager.createQuery(SELECT_ALL_USER, User.class)
                .setFirstResult(offset).setMaxResults(limit).getResultList();
        return UserConverter.convertTo(listUser);
    }
}
