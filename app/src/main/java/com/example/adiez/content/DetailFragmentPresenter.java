package com.example.adiez.content;

import com.example.adiez.content.model.Handler;
import com.example.adiez.content.model.Message;
import com.example.adiez.content.model.Model;

public class DetailFragmentPresenter{

    private final Handler handler;
    private final Model receiver;



    public DetailFragmentPresenter(Handler handler, Model receiver) {
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

    public void deleteMessage(int i) {
        receiver.deleteMessage(i,handler);
    }


}
