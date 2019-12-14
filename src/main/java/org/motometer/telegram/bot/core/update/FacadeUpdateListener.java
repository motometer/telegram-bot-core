package org.motometer.telegram.bot.core.update;

import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.api.Update;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor_ = @Inject)
class FacadeUpdateListener implements UpdateListener {

    private final UpdateListener updateListenerChain;

    @Override
    public void onEvent(Update event) throws BotException {
        updateListenerChain.onEvent(event);
    }
}
