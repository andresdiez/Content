package com.example.adiez.content;

import org.junit.Test;



/**
 * Created by adiez on 2016-02-02.
 */
public class PresenterTest {
    @Test
    public void callValidator(){
        assert (Presenter.email(true));
    }
}
