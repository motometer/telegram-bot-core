package org.motometer.telegram.bot.core.label;

import java.util.Locale;

public interface LabelService {

    Label find(Label.Key key, Locale locale);
}
