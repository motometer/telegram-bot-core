package org.motometer.telegram.bot.core.update;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.core.bot.BotModule;
import org.motometer.telegram.bot.core.dao.DaoModule;
import org.motometer.telegram.bot.core.dao.UserDao;
import org.motometer.telegram.bot.core.update.reply.HelpSendMessageFactory;
import org.motometer.telegram.bot.core.update.reply.HomeSendMessageFactory;

import java.util.Set;

@Module(includes = {DaoModule.class, BotModule.class})
public class UpdateListenerModule {

    @Provides
    public UpdateListener provideUpdateListener(MessageListener messageListener,
                                                CallbackQueryListener callbackQueryListener) {
        return new FacadeUpdateListener(UpdateListenerChain.start()
            .ifPresent(Update::message, messageListener)
            .ifPresent(Update::callbackQuery, callbackQueryListener)
        );
    }

    @Provides
    public MessageListener provideMessageListener(Set<CommandListener> list, Bot bot) {
        return new MessageListener(list, provideHelpCommand(bot));
    }

    @Provides
    public CallbackQueryListener provideCallbackQueryListener(Bot bot) {
        return new CallbackQueryListener(bot);
    }

    @Provides
    @IntoSet
    public CommandListener provideStartCommand(UserDao userDao) {
        return new StartCommandListener(userDao);
    }

    @Provides
    @IntoSet
    public CommandListener provideHelpCommand(Bot bot) {
        return new ReplyCommandListener(bot, new HelpSendMessageFactory(), BotCommand.HELP);
    }

    @Provides
    @IntoSet
    public CommandListener provideHomeCommand(Bot bot) {
        return new ReplyCommandListener(bot, new HomeSendMessageFactory(), BotCommand.HOME);
    }
}
