package org.motometer.core.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.motometer.core.service.model.ImmutableUser;
import org.motometer.core.service.model.User;

public class InMemoryUserDao implements UserDao {

    private static final Map<Long, User> map = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void saveOrUpdate(final User user) {
        final ImmutableUser userWithId = ImmutableUser.copyOf(user)
            .withId(user.telegramUserId());
        map.put(userWithId.id(), user);
    }

    @Override
    public Optional<User> findByUserId(final long userId) {
        return Optional.ofNullable(map.get(userId));
    }
}
