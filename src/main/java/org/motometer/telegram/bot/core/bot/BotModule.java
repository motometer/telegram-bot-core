package org.motometer.telegram.bot.core.bot;

import dagger.Module;
import dagger.Provides;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.client.BotBuilder;
import org.motometer.telegram.bot.core.props.PropertyKey;
import org.motometer.telegram.bot.core.props.PropertyModule;

import java.util.Map;

@Module(includes = {PropertyModule.class})
public class BotModule {

    @Provides
    Bot provideBot(Map<PropertyKey, String> properties) {
        return BotBuilder.defaultBuilder()
            .token(properties.get(PropertyKey.TELEGRAM_TOKEN))
            .apiHost(properties.get(PropertyKey.TELEGRAM_API))
            .build();
    }
}
