package com.stream.tweets.dao;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfigurationSingleton extends TwitterFactory{
    private static Twitter instance;

    private TwitterConfigurationSingleton() {
    }

    public static Twitter getTwitterInstance() {
        if (instance == null) {

            synchronized (TwitterConfigurationSingleton.class) {
                if (instance == null) {
                    System.out.println("init client");
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