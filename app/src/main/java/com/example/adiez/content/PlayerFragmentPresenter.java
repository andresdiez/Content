package com.example.adiez.content;


import com.example.Model;

public class PlayerFragmentPresenter {
    private final Handler handler;
    private final Model model;

    public PlayerFragmentPresenter(Handler handler, Model model) {

        this.handler = handler;
        this.model = model;
    }

    public String getVideo(int position) {
        handler.playVideo(model.getMessage(position).getMessage());
        return null;
    }
}
