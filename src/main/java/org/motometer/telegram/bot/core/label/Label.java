package org.motometer.telegram.bot.core.label;

import org.immutables.value.Value;

import java.util.Locale;

@Value.Immutable
public interface Label {

    @Value.Parameter
    String text();

    @Value.Parameter
    Key key();

    @Value.Parameter
    Locale locale();

    @Value.Immutable
    interface Key {

        @Value.Parameter
        String key();
    }
}
