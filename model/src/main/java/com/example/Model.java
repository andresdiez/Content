package com.example;

import java.util.List;

public interface Model {
    void addMessage(String title, String message, CallBack call);
    void whenModelLoaded(CallBack call);
    Message getMessage(int i);
    void editMessage(int i, String t, String m);
    List<Message> getMessages();
    void deleteMessage(int i, Handler handler);
    void setData(List<Message> data);
}
