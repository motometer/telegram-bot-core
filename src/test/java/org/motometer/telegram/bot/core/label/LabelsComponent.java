package org.motometer.telegram.bot.core.label;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {LabelServiceModule.class})
public interface LabelsComponent {

    void inject(LabelServiceTest test);
}
