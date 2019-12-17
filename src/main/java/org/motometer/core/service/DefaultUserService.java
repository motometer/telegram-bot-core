package org.motometer.core.service;

import lombok.RequiredArgsConstructor;
import org.motometer.core.dao.UserDao;
import org.motometer.core.service.model.User;

@RequiredArgsConstructor
class DefaultUserService implements UserService {

    private final UserDao userDao;

    @Override
    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }
}
