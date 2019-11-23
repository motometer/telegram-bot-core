package org.motometer.telegram.bot.core.spi;

import dagger.Module;
import dagger.Provides;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.WebHookListener;
import org.motometer.telegram.bot.client.BotBuilder;
import org.motometer.telegram.bot.core.props.PropertyKey;
import org.motometer.telegram.bot.core.props.PropertyModule;
import org.motometer.telegram.bot.core.update.UpdateListenerModule;

import java.util.Map;

@Module(includes = {UpdateListenerModule.class, PropertyModule.class})
class CoreModule {

    @Provides
    Bot provideBot(Map<PropertyKey, String> properties) {
        return BotBuilder.defaultBuilder()
            .token(properties.get(PropertyKey.TELEGRAM_TOKEN))
            .apiHost(properties.get(PropertyKey.TELEGRAM_API))
            .build();
    }

    @Provides
    WebHookListener webHookListener(Bot bot, UpdateListener listener) {
        return bot.adaptListener(listener);
    }
}
