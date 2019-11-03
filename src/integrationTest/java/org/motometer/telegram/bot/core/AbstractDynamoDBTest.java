package org.motometer.telegram.bot.core;

import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.motometer.telegram.bot.core.props.PropertyKey;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AbstractDynamoDBTest {

    @Container
    private static GenericContainer dynamoDb = new GenericContainer("amazon/dynamodb-local")
        .withExposedPorts(8000);

    @Getter(AccessLevel.PROTECTED)
    private Map<PropertyKey, String> properties;

    @BeforeEach
    protected void setUp() {
        String host = "http://localhost:" + dynamoDb.getMappedPort(8000);
        Map<PropertyKey, String> properties = new HashMap<>();
        properties.put(PropertyKey.TELEGRAM_API, "");
        properties.put(PropertyKey.TELEGRAM_TOKEN, "token");
        properties.put(PropertyKey.DYNAMODB_HOST, host);
        properties.put(PropertyKey.AWS_REGION, "local");
        properties.put(PropertyKey.AWS_ACCESS_KEY_ID, "DEFAULT_ACCESS_KEY");
        properties.put(PropertyKey.AWS_SECRET_KEY, "DEFAULT_SECRET");
        this.properties = Collections.unmodifiableMap(properties);
    }
}
