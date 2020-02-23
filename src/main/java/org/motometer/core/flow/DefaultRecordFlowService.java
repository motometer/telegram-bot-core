package org.motometer.core.flow;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultRecordFlowService implements RecordFlowService {

    private static final Map<Long, Double> priceMap = Collections.synchronizedMap(new HashMap<>());
    private static final Map<Long, Double> amountMap = Collections.synchronizedMap(new HashMap<>());
    private static final Map<Long, String> stationMap = Collections.synchronizedMap(new HashMap<>());
    private static final Map<Long, ZonedDateTime> timeMap = Collections.synchronizedMap(new HashMap<>());

    private final RecordService recordService;

    @Override
    public RecordFlow findForUser(final long userId) {
        return new Flow(userId, priceMap, amountMap, stationMap, timeMap, recordService);
    }

    @RequiredArgsConstructor
    static class Flow implements RecordFlow {

        private final Long userId;
        private final Map<Long, Double> priceMap;
        private final Map<Long, Double> amountMap;
        private final Map<Long, String> stationMap;
        private final Map<Long, ZonedDateTime> timeMap;
        private final RecordService recordService;

        @Override
        public void time(final ZonedDateTime time) {
            timeMap.put(userId, time);
        }

        @Override
        public void price(final double price) {
            priceMap.put(userId, price);
        }

        @Override
        public void amount(final double amount) {
            amountMap.put(userId, amount);
        }

        @Override
        public void station(final String station) {
            stationMap.put(userId, station);
        }

        @Override
        public void finish() {
            final ImmutableRecord build = ImmutableRecord.builder()
                .time(timeMap.get(userId))
                .station(stationMap.get(userId))
                .price(priceMap.get(userId))
                .amount(amountMap.get(userId))
                .build();
            recordService.save(userId, build);
        }
    }
}
