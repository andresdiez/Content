package com.example.adiez.content.model;

import java.util.List;

public interface Handler {
    void dataDidLoad(List<Message> messages);
    void displayMessage(String t,String m);
    void registerModel(Model model);
}
