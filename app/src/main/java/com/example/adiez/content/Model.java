package com.example.adiez.content;



import java.util.ArrayList;
import java.util.List;


public class Model {

    List<Message> list= new ArrayList<>();




//    public boolean deleteMessage() {
//        return false;
//    }


    public List<Message> getMessage() {

        list.add(0,new Message("title","message"));
        return list;
    }

    public List<Message> addMessage(String title,String message){
        list.add(new Message(title,message));
        return list;
    }



}
