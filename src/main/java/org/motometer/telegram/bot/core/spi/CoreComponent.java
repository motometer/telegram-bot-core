package org.motometer.telegram.bot.core.spi;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = CoreModule.class)
interface CoreComponent {

    void inject(CoreWebHookListenerProvider config);
}
