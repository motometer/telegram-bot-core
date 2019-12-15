package org.motometer.telegram.bot.core.update;

import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.Action;
import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.api.ImmutableCallbackQueryAnswer;
import org.motometer.telegram.bot.api.Update;

@RequiredArgsConstructor
public class CallbackQueryListener implements UpdateListener {

    @Override
    public Action onEvent(Update event) throws BotException {
        return bot -> bot.answerCallbackQuery(ImmutableCallbackQueryAnswer.builder()
            .callbackQueryId(event.callbackQuery().id())
            .text("Answered")
            .build());
    }
}
