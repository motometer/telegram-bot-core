package org.motometer.telegram.bot.core.update;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.core.bot.BotModule;
import org.motometer.telegram.bot.core.dao.DaoModule;
import org.motometer.telegram.bot.core.dao.UserDao;
import org.motometer.telegram.bot.core.update.reply.SendMessageFactory;

import java.util.Set;

@Module(includes = {DaoModule.class, BotModule.class})
public class UpdateListenerModule {

    @Provides
    public UpdateListener provideUpdateListener(Set<CommandListener> list, HelpCommandListener defaultCommand) {
        return new FacadeUpdateListener(list, defaultCommand);
    }

    @Provides
    @IntoSet
    public CommandListener provideStartCommand(UserDao userDao) {
        return new StartCommandListener(userDao);
    }

    @Provides
    public HelpCommandListener provideHelpCommand(Bot bot, SendMessageFactory sendMessageFactory) {
        return new HelpCommandListener(bot, sendMessageFactory);
    }

    @Provides
    public SendMessageFactory provideSendMessageFactory() {
        return new SendMessageFactory();
    }
}
