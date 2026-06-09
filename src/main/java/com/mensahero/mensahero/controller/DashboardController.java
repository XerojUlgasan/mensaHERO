package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.DTO.dashboard.DashboardCount;
import com.mensahero.mensahero.DTO.dashboard.MessagesByKey;
import com.mensahero.mensahero.DTO.dashboard.MessagesOverTime;
import com.mensahero.mensahero.Enums.DateFilters;
import com.mensahero.mensahero.model.Message;
import com.mensahero.mensahero.service.DashboardService;
import com.mensahero.mensahero.service.impl.DashboardServiceImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    DashboardService dashboardService;

    public DashboardController(DashboardServiceImp dashboardServiceImp){
        this.dashboardService = dashboardServiceImp;
    }

    @GetMapping
    public void getDashboardData(UUID ownerId, DateFilters dateFilters) {
        return;
    }

    @GetMapping("/counters")
    public DashboardCount getDashboardCounters(@RequestParam List<UUID> apiIds) {
        return dashboardService.getDashboardCounters(apiIds);
    }

    @GetMapping("/graph")
    public List<MessagesOverTime> getMessagesOvertime(@RequestParam List<UUID> apiIds,@RequestParam  String dateFilter) {
        DateFilters dateFilters = DateFilters.valueOf(dateFilter.toUpperCase()); // UPPER CASE TO MATCH ENUM
        return dashboardService.getMessagesOvertime(apiIds, dateFilters);
    }

    @GetMapping("/byKeys")
    public List<MessagesByKey> getMessagesCountbyKey(@RequestParam List<UUID> apiIds) {
        return dashboardService.getMessagesCountbyKey(apiIds);
    }

    @GetMapping("/recent")
    public List<Message> getRecentMessages(@RequestParam List<UUID> apiIds) {
        return dashboardService.getRecentMessages(apiIds);
    }
}
