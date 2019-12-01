package org.motometer.telegram.bot.core.dao;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import dagger.Module;
import dagger.Provides;
import org.motometer.telegram.bot.core.props.PropertyKey;
import org.motometer.telegram.bot.core.props.PropertyModule;

import java.util.Map;

import static org.motometer.telegram.bot.core.props.PropertyKey.AWS_REGION;

@Module(includes = PropertyModule.class)
public class DynamoDBModule {

    private final AWSCredentialsProvider credentialsProvider;

    public DynamoDBModule() {
        this(new EnvironmentVariableCredentialsProvider());
    }

    public DynamoDBModule(AWSCredentialsProvider credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
    }

    @Provides
    public DynamoDB dynamoDB(Map<PropertyKey, String> properties) {
        AmazonDynamoDB client = provideClient(properties);

        return new DynamoDB(client);
    }

    @Provides
    public AmazonDynamoDB provideClient(Map<PropertyKey, String> properties) {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(properties.get(AWS_REGION))
                .withCredentials(credentialsProvider)
                .build();
    }
}
