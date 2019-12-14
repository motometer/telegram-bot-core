package org.motometer.telegram.bot.core.update;

import org.junit.jupiter.api.Test;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.api.ImmutableUpdate;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.update.reply.MessageFixture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateListenerChainTest {

    @Test
    void chain() throws Exception {
        final CompletableFuture<String> future = new CompletableFuture<>();

        UpdateListener listener = v -> future.complete("Done");

        final Update update = ImmutableUpdate.builder().id(1)
            .message(new MessageFixture().createMessage())
            .build();

        UpdateListenerChain.start()
            .ifPresent(Update::callbackQuery, e -> {
                throw new UnsupportedOperationException("Unexpected call");
            })
            .ifPresent(Update::message, listener)
            .onEvent(update);

        assertThat(future.get(1, TimeUnit.SECONDS)).isEqualTo("Done");
    }
}