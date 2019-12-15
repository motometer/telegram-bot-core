package org.motometer.telegram.bot.core.update.reply;

import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.api.ImmutableReplyKeyboardMarkup;
import org.motometer.telegram.bot.api.ImmutableSendMessage;
import org.motometer.telegram.bot.api.KeyboardButton;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.ReplyMarkup;
import org.motometer.telegram.bot.api.SendMessage;
import org.motometer.telegram.bot.api.User;
import org.motometer.telegram.bot.core.label.LabelKey;
import org.motometer.telegram.bot.core.label.LabelService;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.motometer.telegram.bot.api.ImmutableKeyboardButton.builder;

@RequiredArgsConstructor
public class HomeSendMessageFactory implements SendMessageFactory {

    private final LabelService labelService;

    @Override
    public SendMessage createMessage(Message message) {
        Locale locale = Optional.ofNullable(message.fromUser())
            .map(User::languageCode)
            .map(Locale::forLanguageTag)
            .orElse(Locale.getDefault());

        return ImmutableSendMessage.builder()
            .chatId(message.chat().id())
            .text(labelService.findString(LabelKey.MESSAGE_SELECT_ACTION, locale))
            .replyMarkup(markup(locale))
            .build();
    }

    private ReplyMarkup markup(Locale locale) {
        return ImmutableReplyKeyboardMarkup.builder()
            .resizeKeyboard(true)
            .addKeyboard(button(locale, LabelKey.REPORT_REFUEL))
            .addKeyboard(button(locale, LabelKey.LIST_REFUELS))
            .build();
    }

    private List<KeyboardButton> button(Locale locale, LabelKey key) {
        return singletonList(builder().text(labelService.findString(key, locale)).build());
    }
}
