package com.stream.tweets.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Singleton class for twitter configuration
 */
public class TwitterConfigurationInstance extends TwitterFactory {
    private static Twitter instance;
    private static final Logger logger = LoggerFactory.getLogger(TwitterConfigurationInstance.class);

    private TwitterConfigurationInstance() {
    }

    /**
     * Thread Safe Singleton instance generation
     *
     * @return Twitter
     */
    public static Twitter getTwitterInstance() {
        if (instance == null) {
            synchronized (TwitterConfigurationInstance.class) {
                if (instance == null) {
                    logger.info("Init Twitter Client");
                    ConfigurationBuilder cb = new ConfigurationBuilder();
                    cb.setDebugEnabled(true)
                            .setOAuthConsumerKey("8ml4j0HO3hvZRJhGgZzTKbD9U")
                            .setOAuthConsumerSecret("WO8aRoOoyBfDyHaqU6rxV8It3Yg7KIqwscFkAHmGBxep0pfOj6")
                            .setOAuthAccessToken("795739813596971009-UVc0TZx9BN2fmyXkuBwt2JSDkTHO4wg")
                            .setOAuthAccessTokenSecret("I6EUGqzfLZmPEViI0fnVMNNKBkNbIHyvKZaw9p3KCtvcd");
                    TwitterFactory tf = new TwitterFactory(cb.build());
                    instance = tf.getInstance();
                }
            }
        }
        return instance;
    }
}