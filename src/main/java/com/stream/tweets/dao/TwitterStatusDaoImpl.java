package com.stream.tweets.dao;

import com.stream.tweets.model.StatusListPayload;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import twitter4j.*;

/**
 * Query is properly indexed and should not exceed non-unique key lookup scan for sub class and index scan for primary table
 */
@Repository
public class TwitterStatusDaoImpl implements TwitterStatusDao {

    @Override
    @Transactional
    public StatusListPayload fetchStatusFromSource(String source, Long lastOffsetTime) throws TwitterException {
        Twitter twitter = TwitterConfigurationSingleton.getTwitterInstance();
        Query query = new Query(source);
        if(lastOffsetTime>0) {
            query.sinceId(lastOffsetTime);
        }
        QueryResult result = twitter.search(query);
        return new StatusListPayload(result.getTweets(), result.getMaxId());
    }

    @Override
    public StatusListPayload fetchStatusForUser(String user, Long lastOffsetTime) throws TwitterException {
        Twitter twitter = TwitterConfigurationSingleton.getTwitterInstance();
        return new StatusListPayload(twitter.getUserTimeline(user), lastOffsetTime);
    }
}
    