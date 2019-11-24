package com.stream.tweets.model;

import twitter4j.Status;

import java.util.List;

public class StatusListPayload {
    private List<Status> statusList;
    private Long lastSinceId;

    public StatusListPayload(List<Status> status, Long lastSinceId) {
        this.statusList = status;
        this.lastSinceId = lastSinceId;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public Long getLastSinceId() {
        return lastSinceId;
    }
}
