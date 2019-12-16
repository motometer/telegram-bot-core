package org.motometer.telegram.bot.core.update.actions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.core.label.LabelService;
import org.motometer.telegram.bot.core.update.reply.MessageFixture;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.motometer.telegram.bot.core.update.actions.GenericArgumentMatcher.assertThat;
import static org.motometer.telegram.bot.core.update.reply.SendMessageAssert.assertThatSendMessage;

class HelpActionTest {

    @Test
    void doAction() {
        Bot bot = Mockito.mock(Bot.class);

        //FIXME 123123123 Copy&paste
        LabelService labelService = mock(LabelService.class);

        when(labelService.findString(any(), any()))
            .thenReturn("Мотометер це бот для запису витрат");

        HelpAction action = new HelpAction(labelService, new MessageFixture().withChat().createMessage());

        action.doAction(bot);

        verify(bot).sendMessage(argThat(
            assertThat(sendMessage ->
                assertThatSendMessage(sendMessage)
                    .hasChatId(31231)
                    .textContains("Мотометер це бот для запису витрат")
            ))
        );
    }
}