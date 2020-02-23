package org.motometer.telegram.bot.core.update;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.motometer.core.dao.DaoModule;
import org.motometer.core.service.ServiceModule;
import org.motometer.core.service.UserService;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.api.UpdateListener;
import org.motometer.telegram.bot.core.bot.BotModule;
import org.motometer.telegram.bot.core.label.LabelService;
import org.motometer.telegram.bot.core.label.LabelServiceModule;
import org.motometer.telegram.bot.core.update.actions.HelpAction;
import org.motometer.telegram.bot.core.update.actions.InvalidInputAction;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Module(includes = {DaoModule.class, ServiceModule.class, BotModule.class, LabelServiceModule.class})
public class UpdateListenerModule {

    @Provides
    public UpdateListener provideUpdateListener(MessageListener messageListener,
                                                CallbackQueryListener callbackQueryListener) {
        return UpdateListenerChain.start()
            .ifPresent(Update::message, messageListener)
            .ifPresent(Update::callbackQuery, callbackQueryListener);
    }

    @Provides
    public MessageListener provideMessageListener(Set<CommandListener> listeners, LabelService labelService) {
        Map<BotCommand, UpdateListener> map = listeners.stream()
            .collect(Collectors.toMap(CommandListener::command, v -> v));
        return new MessageListener(map, update -> new InvalidInputAction(labelService, update.message()));
    }

    @Provides
    public CallbackQueryListener provideCallbackQueryListener(LabelService labelService) {
        return new CallbackQueryListener(labelService);
    }

    @Provides
    @IntoSet
    public CommandListener provideStartCommand(UserService userService, LabelService labelService) {
        return new StartCommandListener(userService, labelService);
    }

    @Provides
    @IntoSet
    public CommandListener provideHelpCommand(LabelService labelService) {
        return new ReplyCommandListener(u -> new HelpAction(labelService, u.message()), BotCommand.HELP);
    }
}
