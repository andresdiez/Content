package com.example.adiez.content.model;


import java.util.ArrayList;
import java.util.List;

public final class Model implements CallBackInterface{


    private static List<Message> messages= new ArrayList<>();

    public void loadModel(){ new DataLoader(this).execute(); }

    public static String getMessage(int i){ return messages.get(i).getMessage(); }

    @Override
    public void didLoad(List<Message> messages) { Model.messages=messages; }
}
