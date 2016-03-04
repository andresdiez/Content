package com.example;


import java.util.List;

public class Main extends CallBack {

    public static void main(String args[]) {
        new ModelTest();


    }

    @Override
    public void updateView(List<Message> messages) {
        super.updateView(messages);
        int size=messages.size();
        System.out.println("Size: " + size);
    }
}
