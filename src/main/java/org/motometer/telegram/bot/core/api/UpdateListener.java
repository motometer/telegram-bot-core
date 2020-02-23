package org.motometer.telegram.bot.core.api;

import org.motometer.telegram.bot.api.Update;

public interface UpdateListener {

    Action onEvent(Update update);
}
