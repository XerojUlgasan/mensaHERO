package com.mensahero.mensahero.DTO.dashboard;

import com.mensahero.mensahero.model.Message;

import java.util.List;
import java.util.UUID;

public interface DashboardCount {
    int getTotalMessages();
    int getSuccessRate();
    int getFailedMessages();
}

