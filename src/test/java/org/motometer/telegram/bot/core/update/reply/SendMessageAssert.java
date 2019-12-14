package org.motometer.telegram.bot.core.update.reply;

import org.assertj.core.api.AbstractAssert;
import org.motometer.telegram.bot.api.InlineKeyboardMarkup;
import org.motometer.telegram.bot.api.SendMessage;

import javax.annotation.Nullable;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SendMessageAssert extends AbstractAssert<SendMessageAssert, SendMessage> {

    private SendMessageAssert(@Nullable SendMessage sendMessage) {
        super(sendMessage, SendMessageAssert.class);
    }

    public static SendMessageAssert assertThatSendMessage(Optional<SendMessage> actual) {
        return new SendMessageAssert(actual.orElse(null));
    }

    public SendMessageAssert isPresent() {
        assertThat(actual).isNotNull();
        return this;
    }

    public InlineKeyboardMarkupAssert hasInlineKeyboardMarkup() {
        assertThat(actual.replyMarkup()).isInstanceOf(InlineKeyboardMarkup.class);
        return new InlineKeyboardMarkupAssert((InlineKeyboardMarkup) actual.replyMarkup());
    }
}
