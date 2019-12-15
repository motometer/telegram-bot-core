package org.motometer.telegram.bot.core.update.reply;

import org.motometer.telegram.bot.api.ImmutableSendMessage;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.SendMessage;

public class InvalidInputSendMessageFactory implements SendMessageFactory {

    @Override
    public SendMessage createMessage(Message message) {
        return ImmutableSendMessage.builder()
            .chatId(message.chat().id())
            .replyToMessageId(message.id())
            .text("Я не зміг опрацювати ваше повідомлення.\n"
                + "Перевірте Ваше повідомлення, або спробуйте /start")
            .build();
    }
}
