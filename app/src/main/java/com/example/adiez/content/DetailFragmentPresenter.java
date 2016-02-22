package com.example.adiez.content;


import android.app.Activity;

import com.example.adiez.content.model.Handler;
import com.example.adiez.content.model.Message;
import com.example.adiez.content.model.Receiver;

public class DetailFragmentPresenter{

    private final Handler handler;
    private final Receiver receiver;



    public DetailFragmentPresenter(Handler handler, Receiver receiver) {
        this.handler = handler;
        this.receiver = receiver;

    }

    public void displayMessage(int i){
        Message m=receiver.getMessage(i);
        handler.displayMessage(m.getTitle(),m.getMessage());
    }

    public void onDataChange(int i, String t, String m) {
        receiver.editMessage(i, t, m);
        handler.displayMessage(t, m);
    }

    public void deleteMessage(int i, Activity activity) {
        receiver.deleteMessage(i,activity);
    }


}
