package org.motometer.core.service;

import dagger.Module;
import dagger.Provides;
import org.motometer.core.dao.UserDao;


@Module()
public class ServiceModule {

    @Provides
    UserService provideUserService(UserDao userDao) {
        return new DefaultUserService(userDao);
    }
}
