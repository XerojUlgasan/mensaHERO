package com.mensahero.mensahero.projections.messages;

public interface MessageCounterProjection {
    long getTotalMessages();
    long getFailedMessages();
}
