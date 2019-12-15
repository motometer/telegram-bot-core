package org.motometer.telegram.bot.core.update;

import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.Action;
import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.api.ImmutableCallbackQueryAnswer;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.label.LabelKey;
import org.motometer.telegram.bot.core.label.LabelService;

import java.util.Locale;

@RequiredArgsConstructor
public class CallbackQueryListener implements UpdateListener {

    private final LabelService labelService;

    @Override
    public Action onEvent(Update event) throws BotException {
        return bot -> bot.answerCallbackQuery(ImmutableCallbackQueryAnswer.builder()
            .callbackQueryId(event.callbackQuery().id())
            .text(labelService.findString(LabelKey.CALLBACK_ANSWER, Locale.forLanguageTag("uk")))
            .build());
    }
}
