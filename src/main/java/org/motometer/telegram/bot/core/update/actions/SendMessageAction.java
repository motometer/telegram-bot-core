package org.motometer.telegram.bot.core.update.actions;

import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.SendMessage;
import org.motometer.telegram.bot.core.update.BotMethodAction;

import java.util.function.Supplier;

public class SendMessageAction extends BotMethodAction<SendMessage, Message> {

    public SendMessageAction(Supplier<SendMessage> supplier) {
        super(supplier, Bot::sendMessage);
    }
}
