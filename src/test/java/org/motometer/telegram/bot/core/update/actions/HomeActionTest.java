package org.motometer.telegram.bot.core.update.actions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.core.update.reply.MessageFixture;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.motometer.telegram.bot.core.update.actions.GenericArgumentMatcher.assertThat;
import static org.motometer.telegram.bot.core.update.reply.SendMessageAssert.assertThatSendMessage;

class HomeActionTest {

    @Test
    void doAction() {
        Bot bot = Mockito.mock(Bot.class);

        HomeAction action = new HomeAction(new MessageFixture().withChat().createMessage());

        action.doAction(bot);

        verify(bot).sendMessage(argThat(
            assertThat(sendMessage ->
                assertThatSendMessage(sendMessage)
                    .hasChatId(31231)
                    .textContains("Виберідь дію:")
                    .hasReplyKeyboardMarkup()
                    .hasTotalButtons(2)
                    .hasButton("Записати заправку")
                    .hasButton("Список попередніх заправок")
            ))
        );
    }
}