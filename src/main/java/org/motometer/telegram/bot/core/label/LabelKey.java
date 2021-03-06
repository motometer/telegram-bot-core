package org.motometer.telegram.bot.core.label;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LabelKey implements Label.Key {

    REPORT_REFUEL("button.report.refuel"),
    LIST_REFUELS("button.refuels.list"),
    HELP_MESSAGE("message.help"),
    CALLBACK_ANSWER("message.callback.answer"),
    INVALID_MESSAGE("message.invalid.input"),
    MESSAGE_SELECT_ACTION("message.select.action");

    private final String key;

    @Override
    public String key() {
        return key;
    }
}
