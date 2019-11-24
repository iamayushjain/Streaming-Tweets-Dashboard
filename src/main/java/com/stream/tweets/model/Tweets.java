package com.stream.tweets.model;

/**
 * Tweets response skeleton class
 */
@SuppressWarnings("unused")
public class Tweets {
    private String userName;
    private String userDisplayName;
    private String text;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Tweets(String userName, String userDisplayName, String text) {
        this.userName = userName;
        this.userDisplayName = userDisplayName;
        this.text = text;
    }
}
