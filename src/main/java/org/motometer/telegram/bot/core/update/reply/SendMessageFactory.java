package org.motometer.telegram.bot.core.update.reply;

import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.SendMessage;

public interface SendMessageFactory {

    SendMessage createMessage(Message message);
}
