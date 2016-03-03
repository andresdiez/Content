package com.example.adiez.content;


import com.redspace.model2.CallBack;
import com.redspace.model2.Message;
import com.redspace.model2.Model;

public class DetailFragmentPresenter extends CallBack {

    private final Handler handler;
    private final Model model;



    public DetailFragmentPresenter(Handler handler, Model receiver) {
        this.handler = handler;
        this.model = receiver;

    }

    public void displayMessage(int i){
        Message m= model.getMessage(i);
        handler.displayMessage(m.getTitle(),m.getMessage());
    }

    public void onDataChange(int i, String t, String m) {
        model.editMessage(i, t, m);
        handler.displayMessage(t, m);
    }

    public void deleteMessage(int i) {
        model.deleteMessage(i, this);
    }


}
