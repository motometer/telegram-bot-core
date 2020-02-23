package org.motometer.telegram.bot.core.api;

public interface WebHookListener {

    void onEvent(String update);
}
