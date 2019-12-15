package org.motometer.telegram.bot.core.update.actions;

import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.core.update.reply.InvalidInputSendMessageFactory;

public class InvalidInputAction extends SendMessageAction {

    public InvalidInputAction(Message message) {
        super(new SendMessageSupplier(new InvalidInputSendMessageFactory(), message));
    }
}
