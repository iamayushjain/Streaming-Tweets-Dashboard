package com.stream.tweets.controllers;

import com.stream.tweets.SessionManager;
import com.stream.tweets.model.TweetsListPayload;
import com.stream.tweets.service.TweetsServiceImpl;
import com.stream.tweets.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiveTweetsController {

    private final String LIVE_TWEETS_API = "/hello/{source}";


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private TweetsServiceImpl tweetsServiceImpl;

    @MessageMapping({LIVE_TWEETS_API})
    public void liveTweets(@DestinationVariable String source, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        String sessionId = headerAccessor.getSessionId();
        while(SessionManager.userIdMapping.containsKey(sessionId)){

            TweetsListPayload tweetsListPayload = tweetsServiceImpl.getDataFromTags(source, SessionManager.userIdMapping.get(sessionId));
            simpMessagingTemplate.convertAndSend(Constants.TOPIC_BROKER+"source." + source, tweetsListPayload.getTweetList());

            synchronized (this){
                if(SessionManager.userIdMapping.containsKey(sessionId))
                    SessionManager.userIdMapping.put(sessionId, tweetsListPayload.getLastSinceId());
                else{
                    break;
                }
            }
            Thread.sleep(Constants.REFRESH_RATE); // simulated delay
        }
    }

}
