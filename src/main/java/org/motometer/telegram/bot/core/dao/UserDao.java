package org.motometer.telegram.bot.core.dao;

import org.motometer.telegram.bot.api.User;

import java.util.Optional;

public interface UserDao {

    UserDao init();

    void saveOrUpdate(User user);

    Optional<User> findByUserId(long userId);
}
