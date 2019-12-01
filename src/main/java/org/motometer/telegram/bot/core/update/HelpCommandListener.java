package org.motometer.telegram.bot.core.update;

import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.update.reply.SendMessageFactory;

import javax.inject.Inject;

class HelpCommandListener implements CommandListener {

    private final Bot bot;
    private final SendMessageFactory sendMessageFactory;

    @Inject
    HelpCommandListener(Bot bot, SendMessageFactory sendMessageFactory) {
        this.bot = bot;
        this.sendMessageFactory = sendMessageFactory;
    }

    @Override
    public BotCommand command() {
        return BotCommand.HELP;
    }

    @Override
    public void onEvent(Update event) throws BotException {
        sendMessageFactory.createMessage(event.message())
            .ifPresent(bot::sendMessage);
    }
}
