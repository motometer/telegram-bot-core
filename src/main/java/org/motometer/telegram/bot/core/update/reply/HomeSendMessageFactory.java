package org.motometer.telegram.bot.core.update.reply;

import org.motometer.telegram.bot.api.ImmutableReplyKeyboardMarkup;
import org.motometer.telegram.bot.api.ImmutableSendMessage;
import org.motometer.telegram.bot.api.KeyboardButton;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.ReplyMarkup;
import org.motometer.telegram.bot.core.label.LabelKey;
import org.motometer.telegram.bot.core.label.LabelService;

import java.util.List;
import java.util.Locale;

import static java.util.Collections.singletonList;
import static org.motometer.telegram.bot.api.ImmutableKeyboardButton.builder;

public class HomeSendMessageFactory extends SendMessageFactoryTemplate {

    public HomeSendMessageFactory(LabelService labelService) {
        super(labelService);
    }

    @Override
    public ImmutableSendMessage.Builder customize(ImmutableSendMessage.Builder builder, Message message) {
        Locale locale = toLocale(message);
        return builder
            .text(labelService.findString(LabelKey.MESSAGE_SELECT_ACTION, locale))
            .replyMarkup(markup(locale));
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
