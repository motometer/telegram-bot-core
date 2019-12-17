package org.motometer.core.service;

import org.motometer.core.service.model.User;

public interface UserService {

    void saveOrUpdate(User user);
}
