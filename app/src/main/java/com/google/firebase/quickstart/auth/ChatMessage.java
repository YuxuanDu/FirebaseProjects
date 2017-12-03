package com.google.firebase.quickstart.auth;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yuxuan on 2/12/2017.
 */

public class ChatMessage  {

    private String messageText;
    private String messageUser;
    private String messageTime;

    public ChatMessage(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        messageTime = new SimpleDateFormat("yyy/MM/dd HH:mm:ss").format(new Date());

    }

    public ChatMessage() {
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
