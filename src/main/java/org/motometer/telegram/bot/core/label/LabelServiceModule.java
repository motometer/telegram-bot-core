package org.motometer.telegram.bot.core.label;

import dagger.Module;
import dagger.Provides;

@Module
public class LabelServiceModule {

    @Provides
    public LabelService provideLabelService() {
        return new DefaultLabelService();
    }
}
