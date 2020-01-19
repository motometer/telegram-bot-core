package org.motometer.telegram.bot.core;

import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.AccessLevel;
import lombok.Getter;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.motometer.telegram.bot.core.props.PropertyKey;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class AbstractIntegrationTest {

    @Container
    private static GenericContainer postgres = new PostgreSQLContainer()
        .withExposedPorts(5432);

    @Container
    private static GenericContainer mockTelegramApi = new GenericContainer("rodolpheche/wiremock")
        .withExposedPorts(8080);

    @Getter(AccessLevel.PROTECTED)
    private TestPropertyModule properties;

    @BeforeEach
    protected void setUp() {
        mockTeletramApi();

        properties = new TestPropertyModule(postgres.getMappedPort(5432), mockTelegramApi.getMappedPort(8080));

        Flyway flyway = Flyway.configure()
            .dataSource(
                properties.get(PropertyKey.JDBC_URL),
                properties.get(PropertyKey.JDBC_USER),
                properties.get(PropertyKey.JDBC_PASSWORD)
            )
            .locations("flyway/schema")
            .load();

        flyway.migrate();
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
