package org.motometer.telegram.bot.core.api;

import java.util.function.Function;

import org.motometer.telegram.bot.api.Update;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateReader implements Function<String, Update> {

    private final Gson gson;

    @Override
    public Update apply(final String s) {
        return gson.fromJson(s, Update.class);
    }
}
