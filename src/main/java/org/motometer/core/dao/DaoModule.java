package org.motometer.core.dao;

import dagger.Module;
import dagger.Provides;

import javax.sql.DataSource;

@Module(includes = DataSourceModule.class)
public class DaoModule {

    @Provides
    UserDao provideUserDao(DataSource dataSource) {
        return new UserDaoImpl(dataSource);
    }
}
