package org.motometer.telegram.bot.core.spi;

import dagger.Module;
import dagger.Provides;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.WebHookListener;
import org.motometer.telegram.bot.core.bot.BotModule;
import org.motometer.telegram.bot.core.update.UpdateListenerModule;

@Module(includes = {UpdateListenerModule.class, BotModule.class})
class CoreModule {

    @Provides
    WebHookListener webHookListener(Bot bot, UpdateListener listener) {
        return bot.adaptListener(listener);
    }
}
