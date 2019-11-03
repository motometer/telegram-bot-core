package org.motometer.telegram.bot.core.dao;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import org.motometer.telegram.bot.api.ImmutableUser;
import org.motometer.telegram.bot.api.User;

import javax.annotation.Nullable;
import java.util.Optional;

class UserDaoImpl implements UserDao {

    private final long readCapacityUnits;
    private final long writeCapacityUnits;
    private final DynamoDB dynamoDB;

    UserDaoImpl(DynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
        readCapacityUnits = 5L;
        writeCapacityUnits = 5L;
    }

    @Override
    public void createUsersTable() {
        try {
            dynamoDB.createTable(new CreateTableRequest()
                .withTableName("telegram_users")
                .withProvisionedThroughput(new ProvisionedThroughput()
                    .withReadCapacityUnits(readCapacityUnits)
                    .withWriteCapacityUnits(writeCapacityUnits)
                )
                .withKeySchema(new KeySchemaElement()
                    .withKeyType(KeyType.HASH)
                    .withAttributeName("telegram_user_id")
                )
                .withAttributeDefinitions(new AttributeDefinition()
                    .withAttributeName("telegram_user_id")
                    .withAttributeType(ScalarAttributeType.N)
                )
            );
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveOrUpdate(User user) {
        Table table = dynamoDB.getTable("telegram_users");

        Item item = new Item().withPrimaryKey("telegram_user_id", user.id())
            .withString("first_name", user.firstName())
            .withBoolean("is_bot", user.isBot());
        withItem(item, "last_name", user.lastName());
        withItem(item, "language_code", user.languageCode());
        withItem(item, "username", user.userName());
        table.putItem(item);
    }

    private void withItem(Item item, String attributeName, @Nullable String attributeValue) {
        if (attributeValue != null) {
            item.withString(attributeName, attributeValue);
        }
    }

    @Override
    public Optional<User> findByUserId(long userId) {
        Table table = dynamoDB.getTable("telegram_users");

        GetItemSpec spec = new GetItemSpec().withPrimaryKey("telegram_user_id", userId);

        return Optional.ofNullable(table.getItem(spec))
            .map(outcome -> ImmutableUser.builder()
                .id(outcome.getLong("telegram_user_id"))
                .isBot(outcome.getBoolean("is_bot"))
                .firstName(outcome.getString("first_name"))
                .userName(outcome.getString("username"))
                .build()
            );
    }
}
