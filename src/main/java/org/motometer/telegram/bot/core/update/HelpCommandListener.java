package org.motometer.telegram.bot.core.update;

import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.api.Update;

public class HelpCommandListener implements CommandListener {

    @Override
    public BotCommand command() {
        return BotCommand.HELP;
    }

    @Override
    public void onEvent(Update event) throws BotException {

    }
}
