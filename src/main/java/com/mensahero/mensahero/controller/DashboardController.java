package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.DTO.dashboard.DashboardCount;
import com.mensahero.mensahero.DTO.dashboard.MessagesByKey;
import com.mensahero.mensahero.DTO.dashboard.MessagesOverTime;
import com.mensahero.mensahero.Enums.DateFilters;
import com.mensahero.mensahero.model.Key;
import com.mensahero.mensahero.model.Message;
import com.mensahero.mensahero.service.DashboardService;
import com.mensahero.mensahero.service.KeyService;
import org.springframework.security.core.Authentication;
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
    KeyService keyService;

    public DashboardController(DashboardService dashboardService,  KeyService keyService) {
        this.dashboardService = dashboardService;
        this.keyService = keyService;
    }

    @GetMapping("/test")
    public List<UUID> getApiIds(Authentication auth) {
        return keyService.getKeysId((UUID) auth.getPrincipal());
    }

    @GetMapping("/counters")
    public DashboardCount getDashboardCounters(Authentication auth) {
        List<UUID> apiIds = keyService.getKeysId((UUID) auth.getPrincipal());
        return dashboardService.getDashboardCounters(apiIds);
    }

    @GetMapping("/graph")
    public List<MessagesOverTime> getMessagesOvertime(@RequestParam  String dateFilter, Authentication auth){
        DateFilters dateFilters = DateFilters.valueOf(dateFilter.toUpperCase()); // UPPER CASE TO MATCH ENUM
        List<UUID> apiIds = keyService.getKeysId((UUID) auth.getPrincipal());
        return dashboardService.getMessagesOvertime(apiIds, dateFilters);
    }

    @GetMapping("/byKeys")
    public List<MessagesByKey> getMessagesCountbyKey(Authentication auth) {
        List<UUID> apiIds = keyService.getKeysId((UUID) auth.getPrincipal());
        return dashboardService.getMessagesCountbyKey(apiIds);
    }

    @GetMapping("/recent")
    public List<Message> getRecentMessages(Authentication auth) {
        List<UUID> apiIds = keyService.getKeysId((UUID) auth.getPrincipal());
        return dashboardService.getRecentMessages(apiIds);
    }
}
