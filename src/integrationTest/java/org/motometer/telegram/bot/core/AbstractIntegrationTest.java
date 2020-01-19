package org.motometer.telegram.bot.core;

import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.motometer.telegram.bot.core.props.PropertyKey;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class AbstractIntegrationTest {

    @Container
    private static GenericContainer dynamoDb = new GenericContainer("amazon/dynamodb-local")
        .withExposedPorts(8000);

    @Container
    private static GenericContainer mockTelegramApi = new GenericContainer("rodolpheche/wiremock")
        .withExposedPorts(8080);

    @Getter(AccessLevel.PROTECTED)
    private Map<PropertyKey, String> properties;

    @BeforeEach
    protected void setUp() {
        mockTeletramApi();
        String host = "http://localhost:" + dynamoDb.getMappedPort(8000);
        Map<PropertyKey, String> properties = new HashMap<>();
        properties.put(PropertyKey.TELEGRAM_API, "http://localhost:" + mockTelegramApi.getMappedPort(8080));
        properties.put(PropertyKey.TELEGRAM_TOKEN, "token");
        properties.put(PropertyKey.JDBC_URL, host);
        properties.put(PropertyKey.AWS_REGION, "local");
        properties.put(PropertyKey.AWS_ACCESS_KEY_ID, "DEFAULT_ACCESS_KEY");
        properties.put(PropertyKey.AWS_SECRET_KEY, "DEFAULT_SECRET");
        this.properties = Collections.unmodifiableMap(properties);
    }

    private void mockTeletramApi() {
        WireMock.configureFor(mockTelegramApi.getMappedPort(8080));
        stubFor(post("/bottoken/sendMessage")
            .willReturn(aResponse()
                .withStatus(200)
                .withBody("{ \"ok\": true }"))
        );
    }
}
