package org.motometer.telegram.bot.core.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.motometer.telegram.bot.UpdateListener;
import org.motometer.telegram.bot.api.ChatType;
import org.motometer.telegram.bot.api.ImmutableChat;
import org.motometer.telegram.bot.api.ImmutableMessage;
import org.motometer.telegram.bot.api.ImmutableUpdate;
import org.motometer.telegram.bot.api.ImmutableUser;
import org.motometer.telegram.bot.api.Message;
import org.motometer.telegram.bot.api.Update;
import org.motometer.telegram.bot.api.User;
import org.motometer.telegram.bot.core.AbstractIntegrationTest;
import org.motometer.core.dao.UserDao;
import org.motometer.telegram.bot.core.update.adapters.UserAdapter;
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
            .propertyModule(getProperties())
            .build();

        component.inject(this);
    }

    @Test
    void saveUserOnStart() {
        updateListener.onEvent(newUpdate(() -> newMessage(() -> "/start", () -> newUser("Motometer BOT"))));

        ImmutableUser updatedUser = newUser("Motometer");

        updateListener.onEvent(newUpdate(() -> newMessage(() -> "/start", () -> updatedUser)));

        org.motometer.core.service.model.User user = userDao.findByUserId(203).orElseThrow(IllegalStateException::new);

        assertThat(clearId(user))
            .isEqualTo(clearId(new UserAdapter(updatedUser)));
    }

    private org.motometer.core.service.model.ImmutableUser clearId(org.motometer.core.service.model.User user) {
        return org.motometer.core.service.model.ImmutableUser.copyOf(user).withId(null);
    }

    @Test
    void shouldNotSaveOnMessage() {

        updateListener.onEvent(newUpdate(() -> newMessage(() -> "/help", () -> newUser("Motometer BOT"))));

        Optional<org.motometer.core.service.model.User> user = userDao.findByUserId(203);

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
            .chat(ImmutableChat.builder().id(123).type(ChatType.PRIVATE_CHAT).build())
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