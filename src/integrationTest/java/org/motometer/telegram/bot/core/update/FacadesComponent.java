package org.motometer.telegram.bot.core.update;

import dagger.Component;
import org.motometer.core.service.ServiceModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {UpdateListenerModule.class, ServiceModule.class})
public interface FacadesComponent {

    void inject(FacadeUpdateListenerTest config);
}
