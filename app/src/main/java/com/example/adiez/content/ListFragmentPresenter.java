package com.example.adiez.content;

import com.example.adiez.content.model.CallBack;
import com.example.adiez.content.model.Handler;
import com.example.adiez.content.model.Message;
import com.example.adiez.content.model.Model;

import java.util.List;


public class ListFragmentPresenter implements CallBack {
    private final Model model;
    private final Handler handler;

    public ListFragmentPresenter(Handler handler, Model model) {
        this.model = model;
        this.handler = handler;
    }


    public void build() { model.whenModelLoaded(this); }

    public void addMessage(String title, String message){ model.addMessage(title, message, this); }

    public List<Message> getList(){ return model.getMessages(); }

    @Override
    public void updateView(List<Message> messages) {
        handler.dataDidLoad( messages );
    }
}
