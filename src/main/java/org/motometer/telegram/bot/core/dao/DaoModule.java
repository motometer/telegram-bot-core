package org.motometer.telegram.bot.core.dao;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import dagger.Module;
import dagger.Provides;
import org.motometer.telegram.bot.core.props.PropertyKey;
import org.motometer.telegram.bot.core.props.PropertyModule;

import java.util.Map;

import static org.motometer.telegram.bot.core.props.PropertyKey.AWS_REGION;
import static org.motometer.telegram.bot.core.props.PropertyKey.AWS_SECRET_KEY;
import static org.motometer.telegram.bot.core.props.PropertyKey.DYNAMODB_HOST;

@Module(includes = PropertyModule.class)
public class DaoModule {

    @Provides
    UserDao provideUserDao(DynamoDB dynamoDB) {
        return new UserDaoImpl(dynamoDB);
    }

    @Provides
    DynamoDB dynamoDB(Map<PropertyKey, String> properties) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(endpoint(properties))
            .withCredentials(new MyAWSCredentialsProvider(createCredentials(properties)))
            .build();

        return new DynamoDB(client);
    }

    private AWSCredentials createCredentials(Map<PropertyKey, String> properties) {
        return ImmutableAWSCredentialsImpl.of(
            properties.get(PropertyKey.AWS_ACCESS_KEY_ID), properties.get(AWS_SECRET_KEY)
        );
    }

    private AwsClientBuilder.EndpointConfiguration endpoint(Map<PropertyKey, String> properties) {
        return new AwsClientBuilder.EndpointConfiguration(
            properties.get(DYNAMODB_HOST), properties.get(AWS_REGION)
        );
    }
}
