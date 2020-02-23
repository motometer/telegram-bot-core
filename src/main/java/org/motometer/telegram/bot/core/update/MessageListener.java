package org.motometer.telegram.bot.core.update;

import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.api.Action;
import org.motometer.telegram.bot.core.api.UpdateListener;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class MessageListener implements UpdateListener {

    private final Map<BotCommand, UpdateListener> commandMapListener;
    private final UpdateListener defaultListener;

    @Override
    public Action onEvent(Update event) throws BotException {
        return toCommand(event)
            .map(commandMapListener::get)
            .orElse(defaultListener)
            .onEvent(event);
    }

    private Optional<BotCommand> toCommand(Update event) {
        return Optional.of(event)
            .map(Update::message)
            .map(Message::text)
            .flatMap(BotCommand::of);
    }
}
