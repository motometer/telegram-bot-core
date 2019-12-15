package org.motometer.telegram.bot.core.update.reply;

import org.assertj.core.api.AbstractAssert;
import org.motometer.telegram.bot.api.KeyboardButton;
import org.motometer.telegram.bot.api.ReplyKeyboardMarkup;

import java.util.Collection;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ReplyKeyboardMarkupAssert extends AbstractAssert<ReplyKeyboardMarkupAssert, ReplyKeyboardMarkup> {

    public ReplyKeyboardMarkupAssert(ReplyKeyboardMarkup replyKeyboardMarkup) {
        super(replyKeyboardMarkup, ReplyKeyboardMarkupAssert.class);
    }

    public ReplyKeyboardMarkupAssert hasTotalButtons(int expectedSize) {
        assertThat(buttons())
            .hasSize(expectedSize);
        return this;
    }

    public ReplyKeyboardMarkupAssert hasButton(String expectedText) {
        assertThat(buttons())
            .contains(expectedText);
        return this;
    }

    private Stream<String> buttons() {
        return actual.keyboard().stream().flatMap(Collection::stream).map(KeyboardButton::text);
    }
}
