package org.motometer.telegram.bot.core.label;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.inject.Inject;
import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LabelServiceTest {
    @Inject
    LabelService labelService;

    @BeforeEach
    void setUp() {
        DaggerLabelsComponent.builder().build().inject(this);
    }

    @Test
    void labelServiceCreated() {
        assertThat(labelService).isNotNull();
    }

    @MethodSource("args")
    @ParameterizedTest
    void findLabel(Label.Key key, Locale locale, String expectedText) {

        Label label = labelService.find(key, locale);

        assertThat(label).isNotNull();
        assertThat(label.text()).isEqualTo(expectedText);
        assertThat(label.key()).isEqualTo(key);
        assertThat(label.locale()).isEqualTo(locale);
    }

    static Stream<Arguments> args() {
        return Stream.of(
            Arguments.of(LabelKey.REPORT_REFUEL, Locale.forLanguageTag("uk"), "Записати заправку"),
            Arguments.of(LabelKey.REPORT_REFUEL, Locale.forLanguageTag("ru"), "Записать заправку"),
            Arguments.of(LabelKey.LIST_REFUELS, Locale.forLanguageTag("uk"), "Попередні заправки"),
            Arguments.of(LabelKey.LIST_REFUELS, Locale.forLanguageTag("ru"), "Предыдущие заправки")
        );
    }
}