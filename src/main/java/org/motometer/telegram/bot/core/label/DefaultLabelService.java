package org.motometer.telegram.bot.core.label;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class DefaultLabelService implements LabelService {

    @Override
    public String findString(Label.Key key, Locale locale) {
        final ResourceBundle bundle = ResourceBundle.getBundle("labels", locale);
        byte[] isoBytes = bundle.getString(key.key()).getBytes(StandardCharsets.ISO_8859_1);
        return new String(isoBytes, StandardCharsets.UTF_8);
    }

    @Override
    public Label find(Label.Key key, Locale locale) {
        return ImmutableLabel.of(findString(key, locale), key, locale);
    }
}
