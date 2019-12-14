package org.motometer.telegram.bot.core.update.reply;

import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.SendMessage;

import java.util.Optional;

public interface SendMessageFactory {

    Optional<SendMessage> createMessage(Message message);
}
