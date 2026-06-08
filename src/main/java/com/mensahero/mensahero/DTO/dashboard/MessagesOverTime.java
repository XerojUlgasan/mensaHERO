package com.mensahero.mensahero.DTO.dashboard;

import java.time.OffsetDateTime;

public class MessagesOverTime {
    private OffsetDateTime date;
    private int count;

    /// /////////
    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
