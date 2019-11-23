package org.motometer.telegram.bot.core;

import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.motometer.telegram.bot.WebHookListener;
import org.motometer.telegram.bot.core.spi.CoreWebHookListenerProvider;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Testcontainers
class CoreWebHookListenerTest extends AbstractDynamoDBTest {

    private static final String UPDATE = "/CoreWebHookListenerTest/update.json";
    private WebHookListener listener;


    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        listener = new CoreWebHookListenerProvider(getProperties()).provide();
    }

    @Test
    void validJsonProvided() throws IOException {
        listener.onEvent(IOUtils.resourceToString(UPDATE, Charset.defaultCharset()));
    }

    @Test
    void invalidJsonProvided() {
        assertThatThrownBy(() -> listener.onEvent("this is invalid json"))
            .isInstanceOf(JsonSyntaxException.class)
            .hasCauseInstanceOf(IllegalStateException.class);
    }
}