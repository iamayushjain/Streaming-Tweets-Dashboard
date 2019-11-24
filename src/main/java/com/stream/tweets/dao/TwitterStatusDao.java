package com.stream.tweets.dao;

import com.stream.tweets.model.StatusListPayload;
import twitter4j.TwitterException;

/**
 * Dao interface for fetching tweets
 */
public interface TwitterStatusDao {

    StatusListPayload fetchStatusFromSource(String source, Long lastOffsetTime) throws TwitterException;

    StatusListPayload fetchStatusForUser(String user, Long lastOffsetTime) throws TwitterException;
}
