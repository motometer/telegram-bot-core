package org.motometer.telegram.bot.core.update.adapters;

import lombok.RequiredArgsConstructor;
import org.motometer.core.service.model.User;

import javax.annotation.Nullable;

@RequiredArgsConstructor
public class UserAdapter implements User {

    private final org.motometer.telegram.bot.api.User user;

    @Nullable
    @Override
    public Long id() {
        return null;
    }

    @Override
    public Long telegramUserId() {
        return user.id();
    }

    @Override
    public String firstName() {
        return user.firstName();
    }

    @Override
    public boolean isBot() {
        return user.isBot();
    }

    @Nullable
    @Override
    public String lastName() {
        return user.lastName();
    }

    @Nullable
    @Override
    public String userName() {
        return user.userName();
    }

    @Nullable
    @Override
    public String languageCode() {
        return user.languageCode();
    }
}
