package com.example.adiez.content;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;




public class  ListFragment extends Fragment implements View.OnClickListener {


    private Presenter presenter;

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new Presenter(new Receiver(new Model()));

        View rootView = inflater.inflate(R.layout.list_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment);
        Button button=(Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        presenter.updateMessages();
    }


}
