package org.motometer.telegram.bot.core;

import org.motometer.telegram.bot.core.props.PropertyKey;
import org.motometer.telegram.bot.core.props.PropertyModule;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestPropertyModule extends PropertyModule {

    private final Map<PropertyKey, String> properties;

    public TestPropertyModule(int postgresPort, int telegramApiPort) {
        super(Collections.emptyMap());
        properties = initProperties(postgresPort, telegramApiPort);
    }

    @Override
    public Map<PropertyKey, String> provideProperties() {
        return properties;
    }

    private Map<PropertyKey, String> initProperties(int postgresPort, int telegramApiPort) {
        Map<PropertyKey, String> properties = new HashMap<>();
        properties.put(PropertyKey.TELEGRAM_API, "http://localhost:" + telegramApiPort);
        properties.put(PropertyKey.TELEGRAM_TOKEN, "token");
        properties.put(PropertyKey.JDBC_URL, "jdbc:postgresql://127.0.0.1:" + postgresPort + "/test");
        properties.put(PropertyKey.JDBC_USER, "test");
        properties.put(PropertyKey.JDBC_PASSWORD, "test");
        properties.put(PropertyKey.AWS_REGION, "local");
        properties.put(PropertyKey.AWS_ACCESS_KEY_ID, "DEFAULT_ACCESS_KEY");
        properties.put(PropertyKey.AWS_SECRET_KEY, "DEFAULT_SECRET");
        return Collections.unmodifiableMap(properties);
    }

    public String get(PropertyKey key) {
        return properties.get(key);
    }
}
