package org.motometer.telegram.bot.core.update.actions;

import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.core.label.LabelService;
import org.motometer.telegram.bot.core.update.reply.HomeSendMessageFactory;

public class HomeAction extends SendMessageAction {

    public HomeAction(LabelService labelService, Message message) {
        super(new SendMessageSupplier(new HomeSendMessageFactory(labelService), message));
    }
}
