package com.mensahero.mensahero.service;

import com.mensahero.mensahero.Enums.DateFilters;

import java.util.UUID;

public interface DashboardService {
    // COUNTERS
    public void getDashboardCounters(UUID ownerId);

    // FOR GRAPH
    public void getMessagesOvertime(UUID ownerId, UUID apiId, DateFilters dateFilters);

    // MESSAGE COUNTS PER KEY
    public void getMessagesCountbyKey(UUID ownerId, DateFilters dateFilters);

    // LAST 5 MESSAGES
    public void getRecentMessages(UUID ownerId, DateFilters dateFilters);
}
