package com.example.heli.wechatmoment.network;

public interface NetResponseListener {
    void getUser(String userStr);
    void getTweets(String tweetsStr);
}
