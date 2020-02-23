package org.motometer.core.flow;

import static org.mockito.Mockito.verify;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RecordFlowServiceTest {

    @Test
    void shouldCreateFlow() {
        final RecordService recordService = Mockito.mock(RecordService.class);
        final DefaultRecordFlowService flowService = new DefaultRecordFlowService(recordService);


        final ZonedDateTime now = ZonedDateTime.now();
        final double price = 12.3f;
        final double amount = 35;
        final String station = "UPG";

        flowService.findForUser(1L).time(now);
        flowService.findForUser(1L).price(price);
        flowService.findForUser(1L).amount(amount);
        flowService.findForUser(1L).station(station);
        flowService.findForUser(1L).finish();

        final ImmutableRecord record = ImmutableRecord.builder()
            .amount(amount)
            .price(price)
            .station(station)
            .time(now)
            .build();

        verify(recordService).save(1L, record);
    }
}
