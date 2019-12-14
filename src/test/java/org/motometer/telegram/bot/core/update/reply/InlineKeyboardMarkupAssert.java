package org.motometer.telegram.bot.core.update.reply;

import org.assertj.core.api.AbstractAssert;
import org.motometer.telegram.bot.api.InlineKeyboardButton;
import org.motometer.telegram.bot.api.InlineKeyboardMarkup;

import java.util.Collection;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class InlineKeyboardMarkupAssert extends AbstractAssert<InlineKeyboardMarkupAssert, InlineKeyboardMarkup> {

    public InlineKeyboardMarkupAssert(InlineKeyboardMarkup replyKeyboardMarkup) {
        super(replyKeyboardMarkup, InlineKeyboardMarkupAssert.class);
    }

    public InlineKeyboardMarkupAssert hasTotalButtons(int expectedSize) {
        assertThat(buttons())
            .hasSize(expectedSize);
        return this;
    }

    public InlineKeyboardMarkupAssert hasButton(String expectedText) {
        assertThat(buttons())
            .contains(expectedText);
        return this;
    }

    private Stream<String> buttons() {
        return actual.inlineKeyboard().stream().flatMap(Collection::stream).map(InlineKeyboardButton::text);
    }
}
