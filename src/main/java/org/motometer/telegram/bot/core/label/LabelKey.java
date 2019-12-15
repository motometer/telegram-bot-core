package org.motometer.telegram.bot.core.label;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LabelKey implements Label.Key {

    REPORT_REFUEL("button.report.refuel"),
    LIST_REFUELS("button.refuels.list"),
    ;

    private final String key;

    @Override
    public String key() {
        return key;
    }
}
