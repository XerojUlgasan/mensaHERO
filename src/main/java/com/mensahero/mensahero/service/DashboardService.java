package com.mensahero.mensahero.service;

import com.mensahero.mensahero.DTO.dashboard.DashboardCount;
import com.mensahero.mensahero.DTO.dashboard.MessagesByKey;
import com.mensahero.mensahero.DTO.dashboard.MessagesOverTime;
import com.mensahero.mensahero.Enums.DateFilters;
import com.mensahero.mensahero.model.Message;

import java.util.List;
import java.util.UUID;

public interface DashboardService {
    // COUNTERS
    public DashboardCount getDashboardCounters(List<UUID> apiIds);

    // FOR GRAPH
    public List<MessagesOverTime> getMessagesOvertime(List<UUID> apiIds, DateFilters dateFilters);

    // MESSAGE COUNTS PER KEY
    public List<MessagesByKey> getMessagesCountbyKey(List<UUID> apiIds);

    // LAST 5 MESSAGES
    public List<Message> getRecentMessages(List<UUID> apiIds);
}
