package org.motometer.telegram.bot.core.update.reply;

import org.junit.jupiter.api.Test;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.SendMessage;

import java.util.Optional;

import static org.motometer.telegram.bot.core.update.reply.SendMessageAssert.assertThatSendMessage;

class HomeSendMessageFactoryTest {

    @Test
    void createReplyMessage() {
        HomeSendMessageFactory factory = new HomeSendMessageFactory();

        Message messageFixture = new MessageFixture()
            .withChat()
            .createMessage();

        Optional<SendMessage> result = factory.createMessage(messageFixture);

        assertThatSendMessage(result)
            .isPresent()
            .hasInlineKeyboardMarkup()
            .hasTotalButtons(1)
            .hasButton("Add a vehicle");
    }
}