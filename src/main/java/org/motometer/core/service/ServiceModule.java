package org.motometer.core.service;

import dagger.Module;
import dagger.Provides;
import org.motometer.core.dao.DynamoDBModule;
import org.motometer.core.dao.UserDao;


@Module(includes = DynamoDBModule.class)
public class ServiceModule {

    @Provides
    UserService provideUserService(UserDao userDao) {
        return new DefaultUserService(userDao);
    }

    @Provides
    ReportService provideReportService() {
        return new DefaultReportService();
    }
}
