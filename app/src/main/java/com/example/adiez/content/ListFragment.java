package com.example.adiez.content;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;



public class  ListFragment extends Fragment implements Presenter.Handler {



    private Presenter presenter;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private EditText title;
    private EditText message;
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new Presenter(new Receiver(new Model()),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        final View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        final View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addMessage(title.getText().toString(), message.getText().toString());
                title.setText("");
                message.setText("");
            }
        };

        recyclerView = (RecyclerView) rootView.findViewById(R.id.view);
        LinearLayoutManager llm = new LinearLayoutManager(inflater.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setOnClickListener(clickListener);
        adapter=new RecyclerViewAdapter(new ArrayList<Message>(),this);
        recyclerView.setAdapter(adapter);


        button=(Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(clickListener);

        title=(EditText) rootView.findViewById(R.id.editText);
        title.setInputType(1);
        message=(EditText) rootView.findViewById(R.id.editText2);
        presenter.getMessages();
        

        return rootView;
    }


    @Override
    public void onDataChange(List<Message> messages) {
        adapter.setNewList(messages);
        adapter.notifyDataSetChanged();

    }


}



