package org.motometer.core.dao;

import dagger.Module;
import dagger.Provides;
import org.motometer.telegram.bot.core.props.PropertyKey;
import org.motometer.telegram.bot.core.props.PropertyModule;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Module(includes = PropertyModule.class)
public class DataSourceModule {

    @Provides
    public DataSource provideDataSource(Map<PropertyKey, String> properties) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(properties.get(PropertyKey.JDBC_URL));
        dataSource.setUser(properties.get(PropertyKey.JDBC_USER));
        dataSource.setPassword(properties.get(PropertyKey.JDBC_PASSWORD));
        return dataSource;
    }
}
