package org.motometer.telegram.bot.core.update.reply;

import lombok.RequiredArgsConstructor;
import org.motometer.telegram.bot.api.ImmutableSendMessage;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.SendMessage;
import org.motometer.telegram.bot.api.User;
import org.motometer.telegram.bot.core.label.LabelService;

import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class SendMessageFactoryTemplate implements SendMessageFactory {

    protected final LabelService labelService;

    @Override
    public final SendMessage createMessage(Message message) {

        return customize(defaultBuilder(message), message).build();
    }

    public abstract ImmutableSendMessage.Builder customize(ImmutableSendMessage.Builder builder, Message message);

    protected final Locale toLocale(Message message) {
        return Optional.ofNullable(message.fromUser())
            .map(User::languageCode)
            .map(Locale::forLanguageTag)
            .orElse(Locale.getDefault());
    }

    private ImmutableSendMessage.Builder defaultBuilder(Message message) {
        return ImmutableSendMessage.builder()
            .chatId(message.chat().id());
    }
}
