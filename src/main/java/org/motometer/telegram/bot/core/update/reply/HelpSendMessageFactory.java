package org.motometer.telegram.bot.core.update.reply;

import org.motometer.telegram.bot.api.ImmutableSendMessage;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.SendMessage;

public class HelpSendMessageFactory implements SendMessageFactory {

    @Override
    public SendMessage createMessage(Message message) {
        return ImmutableSendMessage.builder()
            .chatId(message.chat().id())
            .text("Мотометер це бот для запису витрат на автомобіль. \n"
                + "У Мотометер можна записувати витрати на пальне, сервіс та інше.\n"
                + "Доступні команди: \n"
                + "\t/start - початок роботи\n"
                + "\t/help - допомога\n"
                + "\t/feedback - написати відгук, або проблему\n")
            .build();
    }
}
