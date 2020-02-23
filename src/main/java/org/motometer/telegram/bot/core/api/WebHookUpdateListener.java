package org.motometer.telegram.bot.core.api;

import java.util.function.Function;

import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.api.Update;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WebHookUpdateListener implements WebHookListener {

    private final UpdateListener listener;
    private final Bot bot;
    private final Function<String, Update> deserializer;

    @Override
    public void onEvent(final String update) {
        listener.onEvent(deserializer.apply(update)).doAction(bot);
    }
}
