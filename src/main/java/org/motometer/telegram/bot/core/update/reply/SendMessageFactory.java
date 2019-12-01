package org.motometer.telegram.bot.core.update.reply;

import org.motometer.telegram.bot.api.Chat;
import org.motometer.telegram.bot.api.ImmutableSendMessage;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.SendMessage;

import java.util.Optional;

public class SendMessageFactory {

    public Optional<SendMessage> createMessage(Message message) {
        return Optional.ofNullable(message.chat())
            .map(Chat::id)
            .map(chatId -> {
                return ImmutableSendMessage.builder()
                    .chatId(chatId)
                    .replyToMessageId(message.id())
                    .text("Your message accepted.")
                    .build();
            });
    }
}
