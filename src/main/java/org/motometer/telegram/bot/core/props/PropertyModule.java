package org.motometer.telegram.bot.core.props;

import dagger.Module;
import dagger.Provides;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Module
public class PropertyModule {

    private final Map<PropertyKey, String> properties;

    public PropertyModule(Map<PropertyKey, String> properties) {
        this.properties = Collections.unmodifiableMap(new HashMap<>(properties));
    }

    @Provides
    public Map<PropertyKey, String> provideProperties() {
        return properties;
    }
}
