package org.motometer.telegram.bot.core.update.reply;

import org.motometer.telegram.bot.api.Chat;
import org.motometer.telegram.bot.api.ImmutableInlineKeyboardButton;
import org.motometer.telegram.bot.api.ImmutableInlineKeyboardMarkup;
import org.motometer.telegram.bot.api.ImmutableSendMessage;
import org.motometer.telegram.bot.api.InlineKeyboardButton;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.ReplyMarkup;
import org.motometer.telegram.bot.api.SendMessage;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HomeSendMessageFactory implements SendMessageFactory {

    @Override
    public Optional<SendMessage> createMessage(Message message) {
        return Optional.ofNullable(message.chat())
            .map(Chat::id)
            .map(chatId -> ImmutableSendMessage.builder()
                .chatId(chatId)
                .replyMarkup(newReply())
                .text("Text")
                .build()
            );
    }

    private ReplyMarkup newReply() {
        return ImmutableInlineKeyboardMarkup.builder()
            .addInlineKeyboard(buttons())
            .build();
    }

    private List<InlineKeyboardButton> buttons() {
        return Collections.singletonList(ImmutableInlineKeyboardButton.builder()
            .text("Add a vehicle")
            .callbackData("XYZ")
            .build()
        );
    }
}
