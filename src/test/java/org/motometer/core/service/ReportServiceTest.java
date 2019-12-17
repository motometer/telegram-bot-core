package org.motometer.core.service;

import dagger.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.motometer.core.service.model.ImmutableRefuel;
import org.motometer.core.service.model.PetrolType;
import org.motometer.core.service.model.Refuel;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReportServiceTest {

    @Inject
    ReportService reportService;

    @BeforeEach
    void setUp() {
        DaggerReportServiceTest_TestComponent.builder()
            .build()
            .inject(this);
    }

    @Test
    void reportRefuel() {
        Refuel refuel = refuel(LocalDate.of(2019, 12, 12));

        Refuel saved = reportService.reportRefuel(refuel);

        List<Refuel> refuels = reportService.allRefuels();

        assertThat(refuels).hasSize(1)
            .contains(saved);

        Refuel refuel2 = refuel(LocalDate.of(2019, 12, 13));

        Refuel saved2 = reportService.reportRefuel(refuel2);

        List<Refuel> refuels2 = reportService.allRefuels();

        assertThat(refuels2).hasSize(2)
            .contains(saved)
            .contains(saved2);
    }

    private ImmutableRefuel refuel(LocalDate date) {
        return ImmutableRefuel.builder()
            .amount(BigDecimal.valueOf(39.5))
            .price(BigDecimal.valueOf(12.5))
            .petrolStationName("WOG")
            .date(date)
            .petrolType(PetrolType.GAS)
            .vehicleId(1)
            .build();
    }

    @Component(modules = {ServiceModule.class})
    interface TestComponent {

        void inject(ReportServiceTest reportServiceTest);
    }
}