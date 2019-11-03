package org.motometer.telegram.bot.core;

import lombok.Getter;
import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.WebHookListener;
import org.motometer.telegram.bot.core.props.PropertyKey;
import org.motometer.telegram.bot.core.props.PropertyModule;

import javax.inject.Inject;
import java.util.Map;

final class DefaultCoreWebHookListener implements CoreWebHookListener {

    @Inject
    @Getter
    WebHookListener webHookListener;

    DefaultCoreWebHookListener(Map<PropertyKey, String> properties) {
        DaggerCoreComponent.builder()
            .propertyModule(new PropertyModule(properties))
            .build()
            .inject(this);
    }

    @Override
    public void onEvent(String event) throws BotException {
        webHookListener.onEvent(event);
    }
}
