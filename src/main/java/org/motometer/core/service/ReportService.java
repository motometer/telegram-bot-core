package org.motometer.core.service;

import org.motometer.core.service.model.Refuel;

import java.util.List;

public interface ReportService {

    Refuel reportRefuel(Refuel refuel);

    List<Refuel> allRefuels();
}
