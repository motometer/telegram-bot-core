package org.motometer.telegram.bot.core;

import org.junit.jupiter.api.Test;
import org.motometer.telegram.bot.core.api.WebHookListenerProvider;
import org.motometer.telegram.bot.core.api.WebHookListener;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

class CoreWebHookListenerProviderTest {

    @Test
    void spi() {
        ServiceLoader<WebHookListenerProvider> load = ServiceLoader.load(WebHookListenerProvider.class);

        new TestPropertyModule(8000)
            .provideProperties()
            .forEach((key, s) -> System.setProperty(key.name(), s));

        List<WebHookListener> list = StreamSupport.stream(load.spliterator(), false)
            .map(WebHookListenerProvider::provide)
            .collect(Collectors.toList());

        assertThat(list).hasSize(1);
    }
}
