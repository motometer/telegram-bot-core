package org.motometer.telegram.bot.core.api;

import org.motometer.telegram.bot.Bot;

public interface Action {

    static Action empty() {
        return bot -> {
        };
    }

    void doAction(Bot bot);
}
