package org.motometer.telegram.bot.core.label;

import java.util.Locale;
import java.util.ResourceBundle;

public class DefaultLabelService implements LabelService {

    @Override
    public Label find(Label.Key key, Locale locale) {
        final ResourceBundle bundle = ResourceBundle.getBundle("labels", locale);
        final String text = bundle.getString(key.key());
        return ImmutableLabel.of(text, key, locale);
    }
}
