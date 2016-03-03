package com.example.adiez.content;



import com.redspace.model2.Message;

import java.util.List;

public abstract class AbstractHandler implements Handler {
    @Override
    public void dataDidLoad(List<Message> messages){}
    @Override
    public void displayMessage(String t, String m){}
    @Override
    public void goBackToList(){}
    @Override
    public void playVideo(String message){}
}