package org.motometer.core.flow;

import java.time.ZonedDateTime;

public interface RecordFlow {

    void time(final ZonedDateTime now);

    void price(final double price);

    void amount(final double amount);

    void station(final String station);

    void finish();
}
