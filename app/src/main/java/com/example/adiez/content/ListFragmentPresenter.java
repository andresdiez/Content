package com.example.adiez.content;

import com.example.adiez.content.model.Handler;
import com.example.adiez.content.model.Message;
import com.example.adiez.content.model.Receiver;
import java.util.List;


public class ListFragmentPresenter implements CallBack {

    private final Receiver receiver;
    private final Handler handler;

    public ListFragmentPresenter(Handler handler,Receiver receiver){
        this.receiver=receiver;
        this.handler=handler;
    }


    public void build(){ receiver.loadModel(this); }

    public void addMessage(String title, String message){ receiver.addMessage(title, message, this); }

    public List<Message> getList(){ return receiver.getMessages(); }

    @Override
    public void updateView() { handler.dataDidLoad( receiver.getMessages() ); }


}
