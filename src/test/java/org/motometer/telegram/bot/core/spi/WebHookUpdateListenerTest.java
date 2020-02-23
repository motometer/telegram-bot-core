package org.motometer.telegram.bot.core.spi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.api.UpdateListener;
import org.motometer.telegram.bot.core.api.WebHookUpdateListener;

class WebHookUpdateListenerTest {

    @Test
    void onEvent() throws InterruptedException, ExecutionException, TimeoutException {
        final UpdateListener listener = mock(UpdateListener.class);
        final Bot bot = mock(Bot.class);
        final Update update = mock(Update.class);
        final Function<String, Update> deserializer = mock(Function.class);

        when(deserializer.apply("EVENT")).thenReturn(update);
        final CompletableFuture<Bot> expectedBot = new CompletableFuture<>();
        when(listener.onEvent(update)).thenReturn(expectedBot::complete);
        final WebHookUpdateListener webHookUpdateListener = new WebHookUpdateListener(listener, bot, deserializer);

        webHookUpdateListener.onEvent("EVENT");

        assertThat(expectedBot.get(1, TimeUnit.SECONDS)).isSameAs(bot);
    }
}
