package org.motometer.telegram.bot.core.update.reply;

import org.assertj.core.api.AbstractAssert;
import org.motometer.telegram.bot.api.ReplyKeyboardMarkup;
import org.motometer.telegram.bot.api.SendMessage;

import javax.annotation.Nullable;

import static org.assertj.core.api.Assertions.assertThat;

public class SendMessageAssert extends AbstractAssert<SendMessageAssert, SendMessage> {

    private SendMessageAssert(@Nullable SendMessage sendMessage) {
        super(sendMessage, SendMessageAssert.class);
    }

    public static SendMessageAssert assertThatSendMessage(SendMessage actual) {
        return new SendMessageAssert(actual);
    }

    public ReplyKeyboardMarkupAssert hasReplyKeyboardMarkup() {
        assertThat(actual.replyMarkup()).isInstanceOf(ReplyKeyboardMarkup.class);
        return new ReplyKeyboardMarkupAssert((ReplyKeyboardMarkup) actual.replyMarkup());
    }

    public SendMessageAssert hasChatId(int chatId) {
        assertThat(actual.chatId()).isEqualTo(chatId);
        return this;
    }

    public SendMessageAssert textContains(String expectedText) {
        assertThat(actual.text()).isNotEmpty().contains(expectedText);
        return this;
    }
}
