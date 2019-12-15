package org.motometer.telegram.bot.core.update.actions;

import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.core.update.reply.HelpSendMessageFactory;

public class HelpAction extends SendMessageAction {

    public HelpAction(Message message) {
        super(new SendMessageSupplier(new HelpSendMessageFactory(), message));
    }
}
