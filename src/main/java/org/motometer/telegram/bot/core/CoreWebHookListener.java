package org.motometer.telegram.bot.core;

import org.motometer.telegram.bot.WebHookListener;
import org.motometer.telegram.bot.core.props.PropertyKey;

import java.util.Map;

public interface CoreWebHookListener extends WebHookListener {

    static CoreWebHookListener defaultCoreWebHookListener(Map<PropertyKey, String> properties) {
        return new DefaultCoreWebHookListener(properties);
    }
}
