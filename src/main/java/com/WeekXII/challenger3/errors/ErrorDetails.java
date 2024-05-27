package com.WeekXII.challenger3.errors;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private Integer status;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, Integer status, String message, String details) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() { return status; }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
