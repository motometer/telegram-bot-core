package org.motometer.telegram.bot.core.update;

import lombok.RequiredArgsConstructor;

import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
enum BotCommand {
    START("/start"),
    HELP("/help");

    private static final Map<String, BotCommand> INDEX = EnumSet.allOf(BotCommand.class)
        .stream()
        .collect(Collectors.toMap(v -> v.command, v -> v));

    private final String command;

    public static Optional<BotCommand> of(String v) {
        return Optional.ofNullable(INDEX.get(v));
    }
}
