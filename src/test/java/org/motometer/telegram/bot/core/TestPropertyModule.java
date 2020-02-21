package org.motometer.telegram.bot.core;

import org.motometer.telegram.bot.core.props.PropertyKey;
import org.motometer.telegram.bot.core.props.PropertyModule;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestPropertyModule extends PropertyModule {

    private final Map<PropertyKey, String> properties;

    public TestPropertyModule(int telegramApiPort) {
        super(Collections.emptyMap());
        properties = initProperties(telegramApiPort);
    }

    @Override
    public Map<PropertyKey, String> provideProperties() {
        return properties;
    }

    private Map<PropertyKey, String> initProperties(int telegramApiPort) {
        Map<PropertyKey, String> properties = new HashMap<>();
        properties.put(PropertyKey.TELEGRAM_API, "http://localhost:" + telegramApiPort);
        properties.put(PropertyKey.TELEGRAM_TOKEN, "token");
        return Collections.unmodifiableMap(properties);
    }

    public String get(PropertyKey key) {
        return properties.get(key);
    }
}
