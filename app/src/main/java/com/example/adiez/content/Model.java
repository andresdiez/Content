package com.example.adiez.content;

import java.util.ArrayList;
import java.util.List;


public class Model {

    public String addMessage() {
        return null;
    }


    public boolean deleteMessage() {
        return false;
    }


    public List<Message> getMessage() {
        List<Message> list= new ArrayList<>();
        list.add(0,new Message("sad"));
        return list;
    }


    public String editMessage() {
        return null;
    }
}
