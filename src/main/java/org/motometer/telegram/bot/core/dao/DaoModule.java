package org.motometer.telegram.bot.core.dao;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import dagger.Module;
import dagger.Provides;

@Module(includes = DynamoDBModule.class)
public class DaoModule {

    @Provides
    UserDao provideUserDao(DynamoDB dynamoDB) {
        return new UserDaoImpl(dynamoDB);
    }
}
