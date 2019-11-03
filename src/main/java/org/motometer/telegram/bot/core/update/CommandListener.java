package org.motometer.telegram.bot.core.update;

import org.motometer.telegram.bot.UpdateListener;

public interface CommandListener extends UpdateListener {

    BotCommand command();
}
