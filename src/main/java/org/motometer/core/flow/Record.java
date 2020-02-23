package org.motometer.core.flow;

import java.time.ZonedDateTime;

import org.immutables.value.Value;

@Value.Immutable
public interface Record {

    ZonedDateTime time();

    double amount();

    double price();

    String station();
}
