package org.motometer.telegram.bot.core.update;

import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.update.reply.SendMessageFactory;

import javax.inject.Inject;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor_ = @Inject)
class ReplyCommandListener implements CommandListener {

    private final Bot bot;
    private final SendMessageFactory sendMessageFactory;
    private final BotCommand command;

    @Override
    public BotCommand command() {
        return command;
    }

    @Override
    public void onEvent(Update event) throws BotException {
        Optional.ofNullable(event.message())
            .flatMap(sendMessageFactory::createMessage)
            .ifPresent(bot::sendMessage);
    }
}
