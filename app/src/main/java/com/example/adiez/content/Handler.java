package com.example.adiez.content;



import com.example.Message;

import java.util.List;

public interface Handler {
    void dataDidLoad(List<Message> messages);
    void displayMessage(String t, String m);
    void goBackToList();
    void playVideo(String message);
}
