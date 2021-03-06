package org.motometer.telegram.bot.core.update;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.api.Action;
import org.motometer.telegram.bot.core.api.UpdateListener;

import javax.inject.Inject;
import java.util.function.Function;

@RequiredArgsConstructor(onConstructor_ = @Inject, access = AccessLevel.PACKAGE)
public class UpdateListenerChain<T> implements UpdateListener {

    private static final UpdateListener NO_OP = e -> Action.empty();
    private final Function<Update, T> extractor;
    private final UpdateListener listener;
    private final UpdateListener nextListener;

    @Override
    public Action onEvent(Update event) throws BotException {
        if (extractor.apply(event) != null) {
            return listener.onEvent(event);
        }
        return nextListener.onEvent(event);
    }

    public static UpdateListenerChain<Update> start() {
        return new UpdateListenerChain<>(Function.identity(), NO_OP, NO_OP);
    }

    public <V> UpdateListenerChain<V> ifPresent(Function<Update, V> extractor, UpdateListener listener) {
        return new UpdateListenerChain<>(extractor, listener, this);
    }
}
