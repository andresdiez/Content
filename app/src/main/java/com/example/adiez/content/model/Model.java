package com.example.adiez.content.model;


import android.app.Activity;

import com.example.adiez.content.CallBack;
import java.util.ArrayList;
import java.util.List;

public final class Model implements Receiver{

    private List<Message> messages= new ArrayList<>();


    @Override
    public void dataDidLoad(List<Message> messages) { this.messages=messages; }

    @Override
    public Message getMessage(int i) {
        return this.messages.get(i);
    }

    @Override
    public void editMessage(int i, String t, String m) {
        new EditMessage(messages,i).execute(t,m);
    }

    @Override
    public List<Message> getMessages() { return messages; }

    @Override
    public void deleteMessage(int i, Activity activity) {
        new DeleteMessage(messages,activity).execute(i);
    }

    @Override
    public void addMessage(String title, String message, CallBack callBack) {
        new AddMessage(messages,callBack).execute(title, message);

    }

    @Override
    public void loadModel(CallBack call) { new DataLoader(this,call).execute(); }





}
