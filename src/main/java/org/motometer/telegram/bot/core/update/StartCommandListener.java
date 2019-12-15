package org.motometer.telegram.bot.core.update;

import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.BotException;
import org.motometer.telegram.bot.Action;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.dao.UserDao;
import org.motometer.telegram.bot.core.update.actions.HomeAction;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor_ = @Inject)
class StartCommandListener implements CommandListener {

    private final UserDao userDao;

    @Override
    public Action onEvent(Update event) throws BotException {
        Message message = Objects.requireNonNull(event.message());

        Optional.of(message)
            .map(Message::fromUser)
            .ifPresent(userDao::saveOrUpdate);

        return new HomeAction(message);
    }

    @Override
    public BotCommand command() {
        return BotCommand.START;
    }
}
