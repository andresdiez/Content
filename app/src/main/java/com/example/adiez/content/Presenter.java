package com.example.adiez.content;



import android.os.Handler;

import java.util.List;


public class Presenter {

    private final Receiver receiver;

    public Presenter(Receiver receiver){
        this.receiver=receiver;

    }

    public void updateMessages() {

    }

    interface Receiver{
        List<Message> getMessages();
    }

    interface HandlerInt{
        Handler launch();
    }

    public List<Message> getMessages(){
        return receiver.getMessages();
    }

    public Handler launch(){

        return null;
    }




}
