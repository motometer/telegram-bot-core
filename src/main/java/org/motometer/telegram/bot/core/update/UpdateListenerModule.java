package org.motometer.telegram.bot.core.update;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.bot.BotModule;
import org.motometer.telegram.bot.core.dao.DaoModule;
import org.motometer.telegram.bot.core.dao.UserDao;
import org.motometer.telegram.bot.core.update.actions.HelpAction;
import org.motometer.telegram.bot.core.update.actions.InvalidInputAction;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Module(includes = {DaoModule.class, BotModule.class})
public class UpdateListenerModule {

    @Provides
    public UpdateListener provideUpdateListener(MessageListener messageListener,
                                                CallbackQueryListener callbackQueryListener) {
        return UpdateListenerChain.start()
            .ifPresent(Update::message, messageListener)
            .ifPresent(Update::callbackQuery, callbackQueryListener);
    }

    @Provides
    public MessageListener provideMessageListener(Set<CommandListener> listeners) {
        Map<BotCommand, UpdateListener> map = listeners.stream()
            .collect(Collectors.toMap(CommandListener::command, v -> v));
        return new MessageListener(map, update -> new InvalidInputAction(update.message()));
    }

    @Provides
    public CallbackQueryListener provideCallbackQueryListener() {
        return new CallbackQueryListener();
    }

    @Provides
    @IntoSet
    public CommandListener provideStartCommand(UserDao userDao) {
        return new StartCommandListener(userDao);
    }

    @Provides
    @IntoSet
    public CommandListener provideHelpCommand() {
        return new ReplyCommandListener(u -> new HelpAction(u.message()), BotCommand.HELP);
    }
}
