package org.motometer.telegram.bot.core.update;

import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.Action;
import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.api.Update;

import javax.inject.Inject;
import java.util.function.Function;

@RequiredArgsConstructor(onConstructor_ = @Inject)
class ReplyCommandListener implements CommandListener {

    private final Function<Update, Action> factory;
    private final BotCommand command;

    @Override
    public BotCommand command() {
        return command;
    }

    @Override
    public Action onEvent(Update event) throws BotException {
        return factory.apply(event);
    }
}
