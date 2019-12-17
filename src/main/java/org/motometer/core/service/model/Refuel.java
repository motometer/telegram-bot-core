package org.motometer.core.service.model;

import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value.Immutable
public interface Refuel {

    @Nullable
    Long id();

    long vehicleId();

    LocalDate date();

    BigDecimal amount();

    BigDecimal price();

    @Nullable
    String petrolStationName();

    PetrolType petrolType();
}
