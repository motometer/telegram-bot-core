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

    public static final String USER_NAME = "motometer";
    public static final String PASSWORD = "motometer";
    public static final String URL = "jdbc:postgresql://127.0.0.1:5432/postgres";

    @Provides
    public DataSource provideDataSource(Map<PropertyKey, String> properties) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser(properties.get(PropertyKey.JDBC_URL));
        dataSource.setPassword(properties.get(PropertyKey.JDBC_USER));
        dataSource.setURL(properties.get(PropertyKey.JDBC_PASSWORD));
        return dataSource;
    }
}
