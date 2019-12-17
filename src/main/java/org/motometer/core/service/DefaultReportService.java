package org.motometer.core.service;

import org.motometer.core.service.model.Refuel;

import java.util.ArrayList;
import java.util.List;

public class DefaultReportService implements ReportService {

    private final List<Refuel> refuels = new ArrayList<>();

    @Override
    public Refuel reportRefuel(Refuel refuel) {
        refuels.add(refuel);
        return refuel;
    }

    @Override
    public List<Refuel> allRefuels() {
        return new ArrayList<>(refuels);
    }
}
