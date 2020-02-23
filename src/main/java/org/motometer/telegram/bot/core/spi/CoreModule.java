package org.motometer.telegram.bot.core.spi;

import java.util.ServiceLoader;

import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.core.api.UpdateListener;
import org.motometer.telegram.bot.core.api.UpdateReader;
import org.motometer.telegram.bot.core.api.WebHookListener;
import org.motometer.telegram.bot.core.api.WebHookUpdateListener;
import org.motometer.telegram.bot.core.update.UpdateListenerModule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import dagger.Module;
import dagger.Provides;

@Module(includes = { UpdateListenerModule.class })
public class CoreModule {

    @Provides
    public WebHookListener webHookListener(UpdateListener listener, Bot bot) {
        return new WebHookUpdateListener(listener, bot, new UpdateReader(createGson()));
    }

    private static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        for (TypeAdapterFactory factory : ServiceLoader.load(TypeAdapterFactory.class)) {
            gsonBuilder.registerTypeAdapterFactory(factory);
        }
        return gsonBuilder.create();
    }
}
