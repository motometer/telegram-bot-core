package org.motometer.core.service.model;

import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
public interface User {

    @Nullable
    Long id();

    Long telegramUserId();

    String firstName();

    boolean isBot();

    @Nullable
    String lastName();

    @Nullable
    String userName();

    @Nullable
    String languageCode();
}
