package org.motometer.telegram.bot.core.update.actions;

import lombok.RequiredArgsConstructor;
import org.mockito.ArgumentMatcher;

import java.util.function.Consumer;

import static org.motometer.telegram.bot.core.update.reply.SendMessageAssert.assertThatSendMessage;

@RequiredArgsConstructor
class GenericArgumentMatcher<T> extends ArgumentMatcher<T> {

    private final Consumer<T> asserts;

    public static <T> GenericArgumentMatcher<T> assertThat(Consumer<T> asserts) {
        return new GenericArgumentMatcher<>(asserts);
    }

    @Override
    public boolean matches(Object o) {
        asserts.accept((T) o);
        return true;
    }
}
