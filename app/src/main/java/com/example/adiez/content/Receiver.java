package com.example.adiez.content;



import java.util.List;


public class Receiver implements Presenter.Receiver {

    private final Model model;

    public Receiver(Model model) {
        this.model=model;
    }

    @Override
    public List<Message> getMessages() {
        return model.getMessage();
    }
}
