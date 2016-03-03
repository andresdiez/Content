package com.example.adiez.content.context;

import com.example.adiez.content.Handler;
import com.example.adiez.content.DetailFragmentPresenter;
import com.example.adiez.content.ListFragmentPresenter;
import com.example.adiez.content.PlayerFragmentPresenter;
import com.redspace.model2.Model;
import com.redspace.model2.ModelImpl;


public class Context {
    private static final Context instance = new Context();
    public static Context getInstance() { return instance; }

    private final Model model = new ModelImpl();

    public Model provideModel() { return model; }

    public ListFragmentPresenter provideListFragmentPresenter(Handler handler) {
        return new ListFragmentPresenter(handler, provideModel());
    }

    private Context() {}

    public DetailFragmentPresenter provideDetailFragmentPresenter(Handler handler) {
        return new DetailFragmentPresenter(handler,provideModel());
    }

    public PlayerFragmentPresenter providePlayerFragmentPresenter(Handler handler) {
        return new PlayerFragmentPresenter(handler,provideModel());
    }
}
