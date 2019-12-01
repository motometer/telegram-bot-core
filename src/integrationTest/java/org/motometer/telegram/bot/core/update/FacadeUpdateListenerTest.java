package org.motometer.telegram.bot.core.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.api.ImmutableMessage;
import org.motometer.telegram.bot.api.ImmutableUpdate;
import org.motometer.telegram.bot.api.ImmutableUser;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.api.User;
import org.motometer.telegram.bot.core.AbstractIntegrationTest;
import org.motometer.telegram.bot.core.dao.UserDao;
import org.motometer.telegram.bot.core.props.PropertyModule;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.inject.Inject;

import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class FacadeUpdateListenerTest extends AbstractIntegrationTest {

    @Inject
    UpdateListener updateListener;

    @Inject
    UserDao userDao;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        FacadesComponent component = DaggerFacadesComponent.builder()
            .propertyModule(new PropertyModule(getProperties()))
            .dynamoDBModule(new TestDynamoDBModule())
            .build();

        component.inject(this);
    }

    @Test
    void createTableTwice() {
        userDao.init();
        userDao.init();
    }

    @Test
    void saveUserOnStart() {
        updateListener.onEvent(newUpdate(() -> newMessage(() -> "/start", () -> newUser("Motometer BOT"))));

        ImmutableUser updatedUser = newUser("Motometer");

        updateListener.onEvent(newUpdate(() -> newMessage(() -> "/start", () -> updatedUser)));

        User user = userDao.findByUserId(203).orElseThrow(IllegalStateException::new);

        assertThat(user).isEqualTo(updatedUser);
    }

    @Test
    void shouldNotSaveOnMessage() {
        userDao.init();

        updateListener.onEvent(newUpdate(() -> newMessage(() -> "/help", () -> newUser("Motometer BOT"))));

        Optional<User> user = userDao.findByUserId(203);

        assertThat(user).isEmpty();
    }

    private Update newUpdate(Supplier<Message> messageSupplier) {
        return ImmutableUpdate.builder()
            .id(10)
            .message(messageSupplier.get())
            .build();
    }

    private Message newMessage(Supplier<String> textSupplier, Supplier<User> userSupplier) {
        return ImmutableMessage.builder()
            .id(11)
            .fromUser(userSupplier.get())
            .text(textSupplier.get())
            .date(13213)
            .build();
    }

    private ImmutableUser newUser(String firstName) {
        return ImmutableUser.builder()
            .id(203)
            .isBot(false)
            .firstName(firstName)
            .userName("motometer")
            .build();
    }
}