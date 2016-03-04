package com.example.adiez.content;






import com.example.CallBack;
import com.example.Message;
import com.example.Model;

import java.util.List;

public class ListFragmentPresenter extends CallBack {
    private final Model model;
    private final Handler handler;

    public ListFragmentPresenter(Handler handler, Model model) {
        this.model = model;
        this.handler = handler;
    }


    public void build() { model.whenModelLoaded(this); }

    public void addMessage(String title, String message){ model.addMessage(title, message, this); }

    public List<Message> getList(){ return model.getMessages(); }

    @Override
    public void updateView(List<Message> messages) {
        handler.dataDidLoad( messages );
    }
}
