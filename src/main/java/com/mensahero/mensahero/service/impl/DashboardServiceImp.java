package com.mensahero.mensahero.service.impl;

import com.mensahero.mensahero.DTO.dashboard.DashboardCount;
import com.mensahero.mensahero.DTO.dashboard.MessagesByKey;
import com.mensahero.mensahero.DTO.dashboard.MessagesOverTime;
import com.mensahero.mensahero.Enums.DateFilters;
import com.mensahero.mensahero.model.Message;
import com.mensahero.mensahero.service.DashboardService;
import com.mensahero.mensahero.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DashboardServiceImp implements DashboardService {
    MessageService messageService;

    public DashboardServiceImp(MessageServiceImp messageServiceImp) {
        this.messageService = messageServiceImp;
    }

    @Override
    public DashboardCount getDashboardCounters(List<UUID> apiIds) {
        return messageService.getDashboardCount(apiIds);
    }

    @Override
    public List<MessagesOverTime> getMessagesOvertime(List<UUID> apiIds, DateFilters dateFilters) {
        return messageService.getMessagesOverTime(apiIds, dateFilters);
    }

    @Override
    public List<MessagesByKey> getMessagesCountbyKey(List<UUID> apiIds) {
        return messageService.getMessagesByKey(apiIds);
    }

    @Override
    public List<Message> getRecentMessages(List<UUID> apiIds) {
        return messageService.getRecentMessages(apiIds);
    }
}
