package org.motometer.core.dao;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {

    @Provides
    UserDao provideUserDao() {
        return new InMemoryUserDao();
    }
}
