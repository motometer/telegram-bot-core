package org.motometer.core.dao;

import org.motometer.core.service.model.User;

import java.util.Optional;

public interface UserDao {

    void saveOrUpdate(User user);

    Optional<User> findByUserId(long userId);
}
