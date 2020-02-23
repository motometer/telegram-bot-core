package org.motometer.telegram.bot.core.update;

import org.motometer.telegram.bot.core.api.UpdateListener;

public interface CommandListener extends UpdateListener {

    BotCommand command();
}
