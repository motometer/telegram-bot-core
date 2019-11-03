package org.motometer.telegram.bot.core.dao;

import org.motometer.telegram.bot.api.User;

import java.util.Optional;

public interface UserDao {

    void saveOrUpdate(User user);

    void createUsersTable();

    Optional<User> findByUserId(long userId);
}
