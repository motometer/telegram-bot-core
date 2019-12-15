package org.motometer.telegram.bot.core.update.actions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.core.update.reply.MessageFixture;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.motometer.telegram.bot.core.update.actions.GenericArgumentMatcher.assertThat;
import static org.motometer.telegram.bot.core.update.reply.SendMessageAssert.assertThatSendMessage;

class InvalidInputActionTest {

    @Test
    void doAction() {
        Bot bot = Mockito.mock(Bot.class);

        InvalidInputAction action = new InvalidInputAction(new MessageFixture().withChat().createMessage());

        action.doAction(bot);

        verify(bot).sendMessage(argThat(
            assertThat(sendMessage ->
                assertThatSendMessage(sendMessage)
                    .hasChatId(31231)
                    .textContains("Я не зміг опрацювати ваше повідомлення.")
            ))
        );
    }
}