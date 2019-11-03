package org.motometer.telegram.bot.core.update;

import dagger.Component;
import org.motometer.telegram.bot.core.dao.DaoModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {UpdateListenerModule.class, DaoModule.class})
public interface FacadesComponent {

    void inject(FacadeUpdateListenerTest config);
}
