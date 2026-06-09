package com.mensahero.mensahero.DTO.dashboard;

import java.time.OffsetDateTime;

public class MessagesOverTime {
    private OffsetDateTime date;
    private long count;

    public MessagesOverTime() {
    }

    public MessagesOverTime(OffsetDateTime date, long count) {
        this.date = date;
        this.count = count;
    }

    /// /////////
    ///
    ///
    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
