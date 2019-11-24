package com.stream.tweets.model;

public class Tweets {
    String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    String text;

    public Tweets(String user, String text) {
        this.user = user;
        this.text = text;
    }
}
