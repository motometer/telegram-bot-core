package org.motometer.telegram.bot.core.update;

import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.dao.UserDao;

import javax.inject.Inject;
import java.util.Optional;

class StartCommandListener implements CommandListener {

    private final UserDao userDao;

    @Inject
    StartCommandListener(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void onEvent(Update event) throws BotException {
        Optional.of(event)
            .map(Update::message)
            .map(Message::fromUser)
            .ifPresent(userDao::saveOrUpdate);
    }

    @Override
    public BotCommand command() {
        return BotCommand.START;
    }
}
