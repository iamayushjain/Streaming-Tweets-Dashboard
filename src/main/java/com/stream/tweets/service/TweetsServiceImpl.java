package com.stream.tweets.service;

import com.stream.tweets.dao.TwitterStatusDao;
import com.stream.tweets.model.StatusListPayload;
import com.stream.tweets.model.TweetsListPayload;
import com.stream.tweets.model.Tweets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.stream.Collectors;

/**
 * Service implementation for fetching tweets
 */
@Service
public class TweetsServiceImpl {

    @Autowired
    private
    TwitterStatusDao twitterStatusDao;

    public TweetsListPayload getDataFromTags(String source, long lastOffsetTime) throws TwitterException {
        StatusListPayload statusListPayload ;
        if(source.startsWith("@"))
            statusListPayload = twitterStatusDao.fetchStatusForUser(source, lastOffsetTime);
        else
            statusListPayload = twitterStatusDao.fetchStatusFromSource(source, lastOffsetTime);

        System.out.println("-----------------------------------------------------------");
        for (Status status : statusListPayload.getStatusList()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
        return transformPlayListEntityToBO(statusListPayload);
    }

    //convert entity to pojo class
    private TweetsListPayload transformPlayListEntityToBO(StatusListPayload playListEntityList) {
        return new TweetsListPayload(playListEntityList.getStatusList().stream()
                .map(entity -> new Tweets(entity.getUser().getName(), entity.getText()))
                .collect(Collectors.toList()), playListEntityList.getLastSinceId());
    }
}
