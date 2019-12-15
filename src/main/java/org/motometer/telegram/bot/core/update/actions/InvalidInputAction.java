package org.motometer.telegram.bot.core.update.actions;

import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.core.label.LabelService;
import org.motometer.telegram.bot.core.update.reply.InvalidInputSendMessageFactory;

public class InvalidInputAction extends SendMessageAction {

    public InvalidInputAction(LabelService labelService, Message message) {
        super(new SendMessageSupplier(new InvalidInputSendMessageFactory(labelService), message));
    }
}
