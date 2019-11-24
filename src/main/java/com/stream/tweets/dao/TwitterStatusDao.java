package com.stream.tweets.dao;

import com.stream.tweets.model.StatusListPayload;
import twitter4j.TwitterException;

/**
 * Dao interface for fetching tweets
 */
public interface TwitterStatusDao {
    /**
     * function to fetch list of tweets from a particular hash tag or word
     *
     * @param source       :String
     * @param lastOffsetId : Long
     * @return StatusListPayload
     * @throws TwitterException: TwitterException
     */
    StatusListPayload fetchStatusFromSource(String source, Long lastOffsetId) throws TwitterException;

    /**
     * function to fetch list of tweets from a particular username
     *
     * @param user         :String
     * @param lastOffsetId : Long
     * @return StatusListPayload
     * @throws TwitterException: TwitterException
     */
    StatusListPayload fetchStatusForUser(String user, Long lastOffsetId) throws TwitterException;
}
