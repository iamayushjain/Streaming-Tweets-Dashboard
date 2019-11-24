package com.stream.tweets.model;

import java.util.List;

public class TweetsListPayload {
    private List<Tweets> tweets;
    private Long lastSinceId;

    public TweetsListPayload(List<Tweets> tweets, Long lastSinceId) {
        this.tweets = tweets;
        this.lastSinceId = lastSinceId;
    }

    public List<Tweets> getTweetList() {
        return tweets;
    }

    public Long getLastSinceId() {
        return lastSinceId;
    }
}
