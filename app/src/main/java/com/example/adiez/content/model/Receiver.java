package com.example.adiez.content.model;

import android.app.Activity;

import com.example.adiez.content.CallBack;
import java.util.List;

public interface Receiver {

    void addMessage(String title, String message, CallBack call);
    void loadModel(CallBack call);
    void dataDidLoad(List<Message> messages);
    Message getMessage(int i);
    void editMessage(int i, String t, String m);
    List<Message> getMessages();
    void deleteMessage(int i, Activity activity);
}
