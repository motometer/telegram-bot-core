package org.motometer.telegram.bot.core;

import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.WebHookListener;
import org.motometer.telegram.bot.client.BotBuilder;


final class WebHookListenerConfig {

    private WebHookListenerConfig() {

    }

    static WebHookListener webHookListener() {
        Bot bot = bot();
        return bot.adaptListener(e -> { });
    }

    private static Bot bot() {
        return BotBuilder.defaultBuilder()
            .token(System.getenv("TELEGRAM_TOKEN"))
            .build();
    }
}
