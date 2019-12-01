package org.motometer.telegram.bot.core.update;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.motometer.telegram.bot.core.dao.DynamoDBModule;
import org.motometer.telegram.bot.core.props.PropertyKey;

import java.util.Map;

import static org.motometer.telegram.bot.core.props.PropertyKey.AWS_ACCESS_KEY_ID;
import static org.motometer.telegram.bot.core.props.PropertyKey.AWS_REGION;
import static org.motometer.telegram.bot.core.props.PropertyKey.AWS_SECRET_KEY;
import static org.motometer.telegram.bot.core.props.PropertyKey.DYNAMODB_HOST;

public class TestDynamoDBModule extends DynamoDBModule {

    @Override
    public AmazonDynamoDB provideClient(Map<PropertyKey, String> properties) {
        return AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                properties.get(DYNAMODB_HOST), properties.get(AWS_REGION)
            ))
            .withCredentials(provider(properties))
            .build();
    }

    private AWSStaticCredentialsProvider provider(Map<PropertyKey, String> properties) {
        return new AWSStaticCredentialsProvider(
            new BasicAWSCredentials(properties.get(AWS_ACCESS_KEY_ID), properties.get(AWS_SECRET_KEY))
        );
    }
}
