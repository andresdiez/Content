package com.example.adiez.content;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailFragment extends Fragment implements DetailFragmentPresenter.DetailReceiver {


    DetailFragmentPresenter presenter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //presenter=new DetailFragmentPresenter(new DetailReceiver());
        View rootView=inflater.inflate(R.layout.detail_fragment, container, Boolean.parseBoolean(null));


        //Log.e("andres",mBundle.getString("title",""));
        return rootView;
    }



    @Override
    public void displayMessage() {

    }


    @Override
    public void onStart() {
        super.onStart();

        Log.e("andres", getArguments().getString("title", ""));
    }



}
