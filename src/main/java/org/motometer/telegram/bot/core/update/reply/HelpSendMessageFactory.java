package org.motometer.telegram.bot.core.update.reply;

import org.motometer.telegram.bot.api.ImmutableSendMessage;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.core.label.LabelKey;
import org.motometer.telegram.bot.core.label.LabelService;

public class HelpSendMessageFactory extends SendMessageFactoryTemplate {

    public HelpSendMessageFactory(LabelService labelService) {
        super(labelService);
    }

    @Override
    public ImmutableSendMessage.Builder customize(ImmutableSendMessage.Builder builder, Message message) {
        return builder
            .text(labelService.findString(LabelKey.HELP_MESSAGE, toLocale(message)));
    }
}
