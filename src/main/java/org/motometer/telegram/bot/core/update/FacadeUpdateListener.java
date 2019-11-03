package org.motometer.telegram.bot.core.update;

import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.Update;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class FacadeUpdateListener implements UpdateListener {

    private final Map<BotCommand, CommandListener> commandMapListener;
    private final CommandListener defaultListener;

    @Inject
    FacadeUpdateListener(Collection<CommandListener> listeners, CommandListener defaultListener) {
        commandMapListener = listeners.stream().collect(Collectors.toMap(CommandListener::command, v -> v));
        this.defaultListener = defaultListener;
    }

    @Override
    public void onEvent(Update event) throws BotException {
        BotCommand command = toCommand(event);

        CommandListener listener = commandMapListener.getOrDefault(command, defaultListener);

        listener.onEvent(event);
    }

    @Nullable
    private BotCommand toCommand(Update event) {
        return Optional.of(event)
            .map(Update::message)
            .map(Message::text)
            .flatMap(BotCommand::of)
            .orElse(null);
    }
}
