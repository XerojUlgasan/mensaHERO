package com.mensahero.mensahero.DTO.dashboard;

import com.mensahero.mensahero.model.Message;

import java.util.List;
import java.util.UUID;

public class DashboardCount {
    private long totalMessages;
    private double successRate = 0.00;
    private long failedMessages;

    public DashboardCount() {
    }

    public DashboardCount(long totalMessages, long failedMessages) {
        this.totalMessages = totalMessages;
        this.failedMessages = failedMessages;
    }

    public long getTotalMessages() {
        return totalMessages;
    }

    public void setTotalMessages(long totalMessages) {
        this.totalMessages = totalMessages;
    }

    public long getFailedMessages() {
        return failedMessages;
    }

    public void setFailedMessages(long failedMessages) {
        this.failedMessages = failedMessages;
    }

    public double getSuccessRate() {
        this.successRate = ((double) (totalMessages - failedMessages) / totalMessages) * 100;

        return Math.round(successRate * 100.0) / 100.0;
    }
}