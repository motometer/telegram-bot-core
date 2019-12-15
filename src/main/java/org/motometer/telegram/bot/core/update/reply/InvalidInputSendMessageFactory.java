package org.motometer.telegram.bot.core.update.reply;

import org.motometer.telegram.bot.api.ImmutableSendMessage;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.core.label.LabelKey;
import org.motometer.telegram.bot.core.label.LabelService;

public class InvalidInputSendMessageFactory extends SendMessageFactoryTemplate {

    public InvalidInputSendMessageFactory(LabelService labelService) {
        super(labelService);
    }

    @Override
    public ImmutableSendMessage.Builder customize(ImmutableSendMessage.Builder builder, Message message) {
        return builder
            .replyToMessageId(message.id())
            .text(labelService.findString(LabelKey.INVALID_MESSAGE, toLocale(message)));
    }
}
