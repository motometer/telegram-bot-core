package org.motometer.telegram.bot.core.label;

import java.util.Locale;
import java.util.ResourceBundle;

public class DefaultLabelService implements LabelService {

    @Override
    public String findString(Label.Key key, Locale locale) {
        final ResourceBundle bundle = ResourceBundle.getBundle("labels", locale);
        return bundle.getString(key.key());
    }

    @Override
    public Label find(Label.Key key, Locale locale) {
        return ImmutableLabel.of(findString(key, locale), key, locale);
    }
}
