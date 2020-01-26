package org.motometer.telegram.bot.core;

import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class AbstractIntegrationTest {

    @Container
    private static GenericContainer mockTelegramApi = new GenericContainer("rodolpheche/wiremock")
        .withExposedPorts(8080);

    @Getter(AccessLevel.PROTECTED)
    private TestPropertyModule properties;

    @BeforeEach
    protected void setUp() {
        mockTeletramApi();

        properties = new TestPropertyModule(mockTelegramApi.getMappedPort(8080));
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
