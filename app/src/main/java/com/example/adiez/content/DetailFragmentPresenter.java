package com.example.adiez.content;

/**
 * Created by adiez on 2016-02-05.
 */
public class DetailFragmentPresenter {

    private final DetailReceiver detailReceiver;

    public DetailFragmentPresenter(DetailReceiver detailReceiver){
        this.detailReceiver=detailReceiver;

    }


    interface DetailReceiver{
        void displayMessage();

    }



}
