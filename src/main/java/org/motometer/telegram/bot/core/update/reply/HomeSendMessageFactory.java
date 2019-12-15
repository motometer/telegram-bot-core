package org.motometer.telegram.bot.core.update.reply;

import org.motometer.telegram.bot.api.ImmutableReplyKeyboardMarkup;
import org.motometer.telegram.bot.api.ImmutableSendMessage;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.ReplyMarkup;
import org.motometer.telegram.bot.api.SendMessage;

import static java.util.Collections.singletonList;
import static org.motometer.telegram.bot.api.ImmutableKeyboardButton.builder;

public class HomeSendMessageFactory implements SendMessageFactory {

    @Override
    public SendMessage createMessage(Message message) {
        return ImmutableSendMessage.builder()
            .chatId(message.chat().id())
            .text("Виберідь дію:")
            .replyMarkup(markup())
            .build();
    }

    private ReplyMarkup markup() {
        return ImmutableReplyKeyboardMarkup.builder()
            .resizeKeyboard(true)
            .addKeyboard(singletonList(builder().text("Записати заправку").build()))
            .addKeyboard(singletonList(builder().text("Список попередніх заправок").build()))
            .build();
    }
}
