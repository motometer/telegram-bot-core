package org.motometer.telegram.bot.core;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = BotModule.class)
interface CoreComponent {

    void inject(DefaultCoreWebHookListener config);
}
