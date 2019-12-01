package org.motometer.telegram.bot.core.spi;

import org.motometer.telegram.bot.core.props.PropertyKey;

import java.util.function.Function;
import java.util.function.UnaryOperator;

enum PropertyFunction implements Function<PropertyKey, String> {
    INSTANCE;

    @Override
    public String apply(PropertyKey propertyKey) {
        return ((Chain) System::getProperty)
            .defaultIfNull(System::getenv)
            .defaultIfNull(value -> "")
            .apply(propertyKey.name());
    }

    @FunctionalInterface
    private interface Chain extends UnaryOperator<String> {

        default Chain defaultIfNull(Chain orElse) {
            return value -> {
                String result = apply(value);
                if (result == null) {
                    return orElse.apply(value);
                }
                return result;
            };
        }
    }
}
