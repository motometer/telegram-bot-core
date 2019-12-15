package org.motometer.telegram.bot.core.update;

import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.Action;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class BotMethodAction<T, R> implements Action {

    private final Supplier<T> requestFactory;
    private final BiFunction<Bot, T, R> botMethod;
    private final Consumer<R> callback;

    public BotMethodAction(Supplier<T> requestFactory, BiFunction<Bot, T, R> botMethod) {
        this(requestFactory, botMethod, noOp());
    }

    private static <R> Consumer<R> noOp() {
        return r -> {
        };
    }

    @Override
    public void doAction(Bot bot) {
        callback.accept(botMethod.apply(bot, requestFactory.get()));
    }
}
