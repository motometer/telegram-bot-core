package org.motometer.telegram.bot.core.update.actions;

import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.SendMessage;
import org.motometer.telegram.bot.core.update.reply.SendMessageFactory;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class SendMessageSupplier implements Supplier<SendMessage> {

    private final SendMessageFactory factory;
    private final Message message;

    @Override
    public SendMessage get() {
        return factory.createMessage(message);
    }
}
