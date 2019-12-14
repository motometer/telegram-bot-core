package org.motometer.telegram.bot.core.update.reply;

import org.motometer.telegram.bot.api.Chat;
import org.motometer.telegram.bot.api.ChatType;
import org.motometer.telegram.bot.api.ImmutableChat;
import org.motometer.telegram.bot.api.ImmutableMessage;
import org.motometer.telegram.bot.api.Message;

import java.util.Date;

public class MessageFixture {

    private Chat chat;

    public Message createMessage() {
        return ImmutableMessage.builder()
            .date(new Date().getTime())
            .id(123)
            .chat(chat)
            .build();
    }

    public MessageFixture withChat() {
        chat = ImmutableChat.builder()
            .id(31231)
            .type(ChatType.PRIVATE_CHAT)
            .build();
        return this;
    }
}
