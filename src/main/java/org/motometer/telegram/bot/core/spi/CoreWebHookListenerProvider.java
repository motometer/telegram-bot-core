package org.motometer.telegram.bot.core.spi;

import lombok.Getter;
import org.motometer.telegram.bot.WebHookListener;
import org.motometer.telegram.bot.WebHookListenerProvider;
import org.motometer.telegram.bot.core.props.PropertyKey;
import org.motometer.telegram.bot.core.props.PropertyModule;

import javax.inject.Inject;
import java.util.EnumSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CoreWebHookListenerProvider implements WebHookListenerProvider {

    @Inject
    @Getter
    WebHookListener webHookListener;

    public CoreWebHookListenerProvider() {
        this(getProperties());
    }

    public CoreWebHookListenerProvider(Map<PropertyKey, String> properties) {
        DaggerCoreComponent.builder()
            .propertyModule(new PropertyModule(properties))
            .build()
            .inject(this);
    }

    @Override
    public WebHookListener provide() {
        return webHookListener;
    }

    private static Map<PropertyKey, String> getProperties() {
        return EnumSet.allOf(PropertyKey.class)
            .stream()
            .collect(Collectors.toMap(Function.identity(), PropertyFunction.INSTANCE));
    }
}
